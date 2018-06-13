var Fast = {
    config: {
        //toastr默认配置
        toastr: {
            "closeButton": true,
            "debug": false,
            "newestOnTop": false,
            "progressBar": false,
            "positionClass": "toast-top-center",
            "preventDuplicates": false,
            "onclick": null,
            "showDuration": "300",
            "hideDuration": "1000",
            "timeOut": "5000",
            "extendedTimeOut": "1000",
            "showEasing": "swing",
            "hideEasing": "linear",
            "showMethod": "fadeIn",
            "hideMethod": "fadeOut"
        }
    },
    api: {
        //修复URL
        fixurl: function (url) {
            if (url.substr(0, 1) !== "/") {
                var r = new RegExp('^(?:[a-z]+:)?//', 'i');
                if (!r.test(url)) {
                    url =  "./" + url;
                }
            }
            return url;
        },
        //获取修复后可访问的cdn链接
        cdnurl: function (url) {
            return /^(?:[a-z]+:)?\/\//i.test(url) ? url : Config.upload.cdnurl + url;
        },
        //查询Url参数
        query: function (name, url) {
            if (!url) {
                url = window.location.href;
            }
            name = name.replace(/[\[\]]/g, "\\$&");
            var regex = new RegExp("[?&]" + name + "(=([^&#]*)|&|#|$)"),
                results = regex.exec(url);
            if (!results)
                return null;
            if (!results[2])
                return '';
            return decodeURIComponent(results[2].replace(/\+/g, " "));
        },
        open: function (url, title, options) {
            title = title ? title : "";
            url = Fast.api.fixurl(url);
            url = url + (url.indexOf("?") > -1 ? "&" : "?") + "dialog=1";
            var area = [$(window).width() > 800 ? '800px' : '95%', $(window).height() > 600 ? '600px' : '95%'];
            Layer.open($.extend({
                type: 2,
                title: title,
                shadeClose: true,
                shade: false,
                maxmin: true,
                moveOut: true,
                area: area,
                content: url,
                zIndex: Layer.zIndex,
                skin: 'layui-layer-noborder',
                success: function (layero, index) {
                    var that = this;
                    //$(layero).removeClass("layui-layer-border");
                    Layer.setTop(layero);
                    var frame = Layer.getChildFrame('html', index);
                    var layerfooter = frame.find(".layer-footer");
                    Fast.api.layerfooter(layero, index, that);

                    //绑定事件
                    if (layerfooter.size() > 0) {
                        // 监听窗口内的元素及属性变化
                        // Firefox和Chrome早期版本中带有前缀
                        var MutationObserver = window.MutationObserver || window.WebKitMutationObserver || window.MozMutationObserver
                        // 选择目标节点
                        var target = layerfooter[0];
                        // 创建观察者对象
                        var observer = new MutationObserver(function (mutations) {
                            Fast.api.layerfooter(layero, index, that);
                            mutations.forEach(function (mutation) {
                            });
                        });
                        // 配置观察选项:
                        var config = {attributes: true, childList: true, characterData: true, subtree: true}
                        // 传入目标节点和观察选项
                        observer.observe(target, config);
                        // 随后,你还可以停止观察
                        // observer.disconnect();
                    }
                }
            }, options ? options : {}));
            return false;
        },
        layerfooter: function (layero, index, that) {
            var frame = Layer.getChildFrame('html', index);
            var layerfooter = frame.find(".layer-footer");
            if (layerfooter.size() > 0) {
                $(".layui-layer-footer", layero).remove();
                var footer = $("<div />").addClass('layui-layer-btn layui-layer-footer');
                footer.html(layerfooter.html());
                if ($(".row", footer).size() === 0) {
                    $(">", footer).wrapAll("<div class='row'></div>");
                }
                footer.insertAfter(layero.find('.layui-layer-content'));
            }
            var heg = frame.outerHeight();
            var titHeight = layero.find('.layui-layer-title').outerHeight() || 0;
            var btnHeight = layero.find('.layui-layer-btn').outerHeight() || 0;

            var oldheg = heg + titHeight + btnHeight;
            var maxheg = $(window).height() < 600 ? $(window).height() : 600;
            if (frame.outerWidth() < 768 || that.area[0].indexOf("%") > -1) {
                maxheg = $(window).height();
            }
            // 如果有.layer-footer或窗口小于600则重新排
            if (layerfooter.size() > 0 || oldheg < maxheg || that.area[0].indexOf("%") > -1) {
                var footerHeight = layero.find('.layui-layer-footer').outerHeight() || 0;
                footerHeight = 0;
                if (oldheg >= maxheg) {
                    heg = Math.min(maxheg, oldheg) - titHeight - btnHeight - footerHeight;
                }
                layero.css({height: heg + titHeight + btnHeight + footerHeight});
                layero.find("iframe").css({height: heg});
            }
            if (layerfooter.size() > 0) {
                footer.on("click", ".btn", function () {
                    if ($(this).hasClass("disabled") || $(this).parent().hasClass("disabled")) {
                        return;
                    }
                    $(".btn:eq(" + $(this).index() + ")", layerfooter).trigger("click");
                });
            }
        },
        success: function (options, callback) {
            var type = typeof options === 'function';
            if (type) {
                callback = options;
            }
            return Layer.msg(__('Operation completed'), $.extend({
                offset: 0, icon: 1
            }, type ? {} : options), callback);
        },
        error: function (options, callback) {
            var type = typeof options === 'function';
            if (type) {
                callback = options;
            }
            return Layer.msg(__('Operation failed'), $.extend({
                offset: 0, icon: 2
            }, type ? {} : options), callback);
        },
        Toastr: toastr,
        Layer: layer
    },
    init: function () {
        // 对相对地址进行处理
        $.ajaxSetup({
            beforeSend: function (xhr, setting) {
                setting.url = Fast.api.fixurl(setting.url);
            }
        });
        // 绑定ESC关闭窗口事件
        $(window).keyup(function (e) {
            if (e.keyCode == 27) {
                if ($(".layui-layer").size() > 0) {
                    var index = 0;
                    $(".layui-layer").each(function () {
                        index = Math.max(index, parseInt($(this).attr("times")));
                    });
                    if (index) {
                        Layer.close(index);
                    }
                }
            }
        });

        //公共代码
        //配置Toastr的参数
        Toastr.options = Fast.config.toastr;
    }
};
//将Layer暴露到全局中去
window.Layer = layer;
//将Toastr暴露到全局中去;
window.Toastr = toastr;
//将Fast渲染至全局
window.Fast = Fast;

//默认初始化执行的代码
Fast.init();

var Backend = {
    api: {
        sidebar: function (params) {
            colorArr = ['red', 'green', 'yellow', 'blue', 'teal', 'orange', 'purple'];
            $colorNums = colorArr.length;
            badgeList = {};
            $.each(params, function (k, v) {
                $url = Fast.api.fixurl(k);

                if ($.isArray(v)) {
                    $nums = typeof v[0] !== 'undefined' ? v[0] : 0;
                    $color = typeof v[1] !== 'undefined' ? v[1] : colorArr[(!isNaN($nums) ? $nums : $nums.length) % $colorNums];
                    $class = typeof v[2] !== 'undefined' ? v[2] : 'label';
                } else {
                    $nums = v;
                    $color = colorArr[(!isNaN($nums) ? $nums : $nums.length) % $colorNums];
                    $class = 'label';
                }
                //必须nums大于0才显示
                badgeList[$url] = $nums > 0 ? '<small class="' + $class + ' pull-right bg-' + $color + '">' + $nums + '</small>' : '';
            });
            $.each(badgeList, function (k, v) {
                var anchor = top.window.$("li a[addtabs][url='" + k + "']");
                if (anchor) {
                    top.window.$(".pull-right-container", anchor).html(v);
                    top.window.$(".nav-addtabs li a[node-id='" + anchor.attr("addtabs") + "'] .pull-right-container").html(v);
                }
            });
        },
        addtabs: function (url, title, icon) {
            var dom = "a[url='{url}']";
            var leftlink = top.window.$(dom.replace(/\{url\}/, url));
            if (leftlink.size() > 0) {
                leftlink.trigger("click");
            } else {
                url = Fast.api.fixurl(url);
                leftlink = top.window.$(dom.replace(/\{url\}/, url));
                if (leftlink.size() > 0) {
                    var event = leftlink.parent().hasClass("active") ? "dblclick" : "click";
                    leftlink.trigger(event);
                } else {
                    var baseurl = url.substr(0, url.indexOf("?") > -1 ? url.indexOf("?") : url.length);
                    leftlink = top.window.$(dom.replace(/\{url\}/, baseurl));
                    //能找到相对地址
                    if (leftlink.size() > 0) {
                        icon = typeof icon != 'undefined' ? icon : leftlink.find("i").attr("class");
                        title = typeof title != 'undefined' ? title : leftlink.find("span:first").text();
                        leftlink.trigger("fa.event.toggleitem");
                    }
                    var navnode = $(".nav-tabs ul li a[node-url='" + url + "']");
                    if (navnode.size() > 0) {
                        navnode.trigger("click");
                    } else {
                        //追加新的tab
                        var id = Math.floor(new Date().valueOf() * Math.random());
                        icon = typeof icon != 'undefined' ? icon : 'fa fa-circle-o';
                        title = typeof title != 'undefined' ? title : '';
                        top.window.$("<a />").append('<i class="' + icon + '"></i> <span>' + title + '</span>').prop("href", url).attr({
                            url: url,
                            addtabs: id
                        }).addClass("hide").appendTo(top.window.document.body).trigger("click");
                    }
                }
            }
        }
    },
    init: function () {
        //公共代码
        //配置Toastr的参数
        Toastr.options.positionClass = "toast-top-right-index";
        //点击包含.btn-dialog的元素时弹出dialog
        $(document).on('click', '.btn-dialog,.dialogit', function (e) {
            e.preventDefault();
            Backend.api.open(Backend.api.fixurl($(this).attr('href')), $(this).attr('title'));
        });
        //点击包含.btn-addtabs的元素时事件
        $(document).on('click', '.btn-addtabs,.addtabsit', function (e) {
            e.preventDefault();
            Backend.api.addtabs($(this).attr("href"), $(this).attr("title"));
        });
        //修复含有fixed-footer类的body边距
        if ($(".fixed-footer").size() > 0) {
            $(document.body).css("padding-bottom", $(".fixed-footer").height());
        }
    }
};
Backend.api = $.extend(Fast.api);
//将Backend渲染至全局,以便于在子框架中调用
window.Backend = Backend;
Backend.init();
