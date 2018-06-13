var BaseUrl = "";

/*首页菜单*/
$.getJSON(BaseUrl + "/cms/menu/allMenu", function (result) {
    console.log(result);
    if (result.code === 200) {
        var html = '';
        var footHtml = '';
        for (var i = 0; i < result.data.length; i++) {
            var menu = result.data[i];
            html += '<li class="about">\n' +
                '        <a href="' + BaseUrl + 'detail.html?flag=' + result.data[i].model.flag + '&menuId=' + result.data[i].child[0].model.id + '" onMouseOut="MM_swapImgRestore()">' +
                '           <img src="' + BaseUrl + result.data[i].model.showpic + '"\n' +
                '                name="Image' + i + '" width="120" height="38" border="0" id="Image' + i + '"/>\n' +
                '        </a>\n' +
                '        <ul style="width:150px;">\n';
            for (var j = 0; j < result.data[i].child.length; j++) {
                html += '  <li><a href="' + BaseUrl + 'detail.html?flag=' + result.data[i].model.flag + '&menuId=' + result.data[i].child[j].model.id + '">' + result.data[i].child[j].model.name + '</a></li>\n';
            }
            html += '        </ul>\n' +
                '    </li>';

            footHtml += '|\n<a href="' + BaseUrl + 'detail.html?siteFlag=banner&flag=' + result.data[i].model.flag  + '&menuId=' + result.data[i].child[0].model.id + '" class="lxtext_footer">' + result.data[i].model.name + '</a>';
        }
        $("#menu").append(html);
        $("#footMenu").append(footHtml);
        ddsmoothmenu.init({
            mainmenuid: "smoothmenu1", //menu DIV id
            //customtheme: ["#1c5a80", "#18374a"], //override default menu CSS background values? Uncomment: ["normal_background", "hover_background"]
            contentsource: "markup" //"markup" or ["container_id", "path_to_menu_file"]
        })
    } else {
        alert(result.message);
    }
});

/**banner*/
var flag = getQueryString("flag", 'index');
$.getJSON(BaseUrl + "/cms/pic/siteFlag", {siteFlag: "banner", picFlag: flag}, function (result) {
    console.log(result);
    if (result.code === 200) {
        $("#banner").html('<img src="' + BaseUrl + result.data.path + '" width="940" height="284"/>');
    } else {
        alert(result.message);
    }
});

function dateFtt(fmt,jsondate) { //author: meizz

    var date = new Date(parseInt(jsondate, 10));

    var o = {
        "M+" : date.getMonth()+1,                 //月份
        "d+" : date.getDate(),                    //日
        "h+" : date.getHours(),                   //小时
        "m+" : date.getMinutes(),                 //分
        "s+" : date.getSeconds(),                 //秒
        "q+" : Math.floor((date.getMonth()+3)/3), //季度
        "S"  : date.getMilliseconds()             //毫秒
    };
    if(/(y+)/.test(fmt))
        fmt=fmt.replace(RegExp.$1, (date.getFullYear()+"").substr(4 - RegExp.$1.length));
    for(var k in o)
        if(new RegExp("("+ k +")").test(fmt))
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
    return fmt;
}

function getQueryString(name, def) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var result = window.location.search.substr(1).match(reg);
    return result ? decodeURIComponent(result[2]) : def;
}
