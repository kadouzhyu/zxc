toastr.options = {
    "closeButton": true,
    "debug": false,
    "newestOnTop": false,
    "progressBar": false,
    "positionClass": "toast-top-center",
    "preventDuplicates": false,
    "onclick": null,
    "showDuration": "5000",
    "hideDuration": "1000",
    "timeOut": "100000",
    "extendedTimeOut": "0",
    "showEasing": "swing",
    "hideEasing": "linear",
    "showMethod": "fadeIn",
    "hideMethod": "fadeOut"
};


//全屏事件
$(document).on('click', "[data-toggle='fullscreen']", function () {
    var doc = document.documentElement;
    if ($(document.body).hasClass("full-screen")) {
        $(document.body).removeClass("full-screen");
        document.exitFullscreen ? document.exitFullscreen() : document.mozCancelFullScreen ? document.mozCancelFullScreen() : document.webkitExitFullscreen && document.webkitExitFullscreen();
    } else {
        $(document.body).addClass("full-screen");
        doc.requestFullscreen ? doc.requestFullscreen() : doc.mozRequestFullScreen ? doc.mozRequestFullScreen() : doc.webkitRequestFullscreen ? doc.webkitRequestFullscreen() : doc.msRequestFullscreen && doc.msRequestFullscreen();
    }
});

//修复iOS下iframe无法滚动的BUG
if (/iPad|iPhone|iPod/.test(navigator.userAgent) && !window.MSStream) {
    $(".tab-addtabs").addClass("ios-iframe-fix");
}


//窗口大小改变,修正主窗体最小高度
$(window).resize(function () {
    $(".tab-addtabs").css("height", $(".content-wrapper").height() + "px");
});


//修复iOS下iframe无法滚动的BUG
if (/iPad|iPhone|iPod/.test(navigator.userAgent) && !window.MSStream) {
    $(".tab-addtabs").addClass("ios-iframe-fix");
}

//保留最后一次点击的窗口
$(document).on("click", "a[addtabs]", function (e) {
    localStorage.setItem("lastpage", $(this).attr("url"));
});

//绑定tabs事件
$('#nav').addtabs({iframeHeight: "100%"});

//窗口大小改变,修正主窗体最小高度
$(window).resize(function () {
    $(".tab-addtabs").css("height", $(".content-wrapper").height() + "px");
});

//双击重新加载页面
$(document).on("dblclick", ".sidebar-menu li > a", function (e) {
    $("#tab_" + $(this).attr("addtabs") + " iframe").attr('src', function (i, val) {
        return val;
    });
    e.stopPropagation();
});

//切换左侧sidebar显示隐藏
$(document).on("click fa.event.toggleitem", ".sidebar-menu li > a", function (e) {
    $(".sidebar-menu li").removeClass("active");
    //当外部触发隐藏的a时,触发父辈a的事件
    if (!$(this).closest("ul").is(":visible")) {
        //如果不需要左侧的菜单栏联动可以注释下面一行即可
        $(this).closest("ul").prev().trigger("click");
    }

    var visible = $(this).next("ul").is(":visible");
    if (!visible) {
        $(this).parents("li").addClass("active");
    } else {
    }
    //e.stopPropagation();
});

function open(title, url) {
    layer.open({
        type: 2,
        title: title,
        shadeClose: true,
        shade: false,
        maxmin: true, //开启最大化最小化按钮
        area: ['893px', '600px'],
        content: url
    });
}

$('.date-time').datetimepicker({
    autoclose: true,
    todayHighlight: true,
    language: 'zh-CN'
});

$('.date-day').datetimepicker({
    autoclose: true,
    todayHighlight: true,
    language: 'zh-CN',
    minView: 2
});

/**
 * 上传文件的插件
 * name input 的id
 * imageSrc 图片的路径
 */
function uploadFile(name, imageSrc) {
    if (imageSrc == null) {
        $("#" + name).fileinput({
            showRemove: true,//显示移除按钮
            showUpload: false,
            language: 'zh',//语言
            uploadAsync: true,
            allowedPreviewTypes: ['image'],
            //allowedFileExtensions : [ 'png', 'jpg' ],//后缀
            showPreview: true,//预览
            elErrorContainer: "#errorBlock",
            browseClass: "btn btn-primary btn-sm", //按钮样式
            removeIcon: '<i></i>',
            browseIcon: '<i class=""></i>',
            previewFileIcon: '<i class=""></i>',
            removeClass: "btn btn-info btn-sm"
        });
    } else {
        $("#" + name).fileinput({
            showRemove: true,//显示移除按钮
            showUpload: false,
            language: 'zh',//语言
            uploadAsync: true,
            allowedPreviewTypes: ['image'],
            initialPreview: [
                '<img src=' + imageSrc + ' class="file-preview-image"/>'],
            showPreview: true,//预览
            elErrorContainer: "#errorBlock",
            browseClass: "btn btn-primary btn-sm", //按钮样式
            removeIcon: '<i></i>',
            browseIcon: '<i class=""></i>',
            previewFileIcon: '<i class=""></i>',
            removeClass: "btn btn-info btn-sm"
        });
    }
};








