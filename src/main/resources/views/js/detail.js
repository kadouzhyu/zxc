/*左边菜单*/
var menuId = getQueryString("menuId", "");
var flag = getQueryString("flag", "");
$.getJSON(BaseUrl + 'cms/menu/pageMenu', {menuId: menuId}, function (result) {
    console.log(result);
    if (result.code === 200) {
        /*$("#parentMenu").html('<img src="' + BaseUrl + result.data.parent.showpic + '" width="231" height="46">');*/
        var html = '';
        for (var i = 0; i < result.data.child.length; i++) {
            html += '     <tr>\n' +
                '         <td height="19" align="left" class="bg_xx" >\n';
            if (menuId === result.data.child[i].id) {
                html += '       <a href="' + BaseUrl + 'detail.html?flag=' + flag + '&menuId=' + result.data.child[i].id + '" class="lxmenu_o">';
            } else {
                html += '       <a href="' + BaseUrl + 'detail.html?flag=' + flag + '&menuId=' + result.data.child[i].id + '" class="lxmenu">';
            }
            html += result.data.child[i].name +
                '</a>\n' +
                '         </td>\n' +
                '     </tr>';
        }
        $("#childMenu").append(html);
    } else {
        alert(result.message);
    }
});

if (menuId === '64e8f2e29e2f46d1b00558df7da1b593') {
    /*新闻*/
    $.getJSON(BaseUrl + "/cms/article/listBySite",{pageSize:5,site:'news'},function(result){
        console.log(result);
        if (result.code === 200) {
            var html = '  <table width="696" border="0" cellspacing="0" cellpadding="0">\n' +
                '                <tbody>\n' +
                '                <tr>\n' +
                '                    <td colspan="2"><img src="img/hd/ir_press.gif" width="694" height="46"></td>\n' +
                '                </tr>\n' +
                '                <tr>\n' +
                '                    <td width="17"><p class="text">&nbsp;</p></td>\n' +
                '                    <td width="679">\n' +
                '                        <table width="684" border="0" cellspacing="0" cellpadding="0">\n' +
                '                            <tbody>\n' +
                '                            <tr>\n' +
                '                                <td class="text">\n' +
                '                                    <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">\n' +
                '                                        <tbody>\n';

            for (var i = 0; i < result.data.list.length; i++) {
                var article = result.data.list[i];
                html += '                                        <tr>\n' +
                    '                                            <td width="20" valign="top" class="textTable_ir">&nbsp;</td>\n' +
                    '                                            <td width="109" valign="top" class="textTable_ir"><span class="date"><span\n' +
                    '                                                    name="zoom">' + dateFtt('yyyy年MM月dd日', article.addtime) + '</span></span></td>\n' +
                    '                                            <td width="534" valign="top" class="textTable_ir"><a\n' +
                    '                                                    href="' + BaseUrl + 'news.html?article=' + article.id + '" target="_blank"\n' +
                    '                                                    class="lxtext"><span name="zoom">' + article.title + '</span></a>\n' +
                    '                                            </td>\n' +
                    '                                            <td width="21" valign="top" class="textTable_ir">&nbsp;</td>\n' +
                    '                                        </tr>\n';
            }


            html += '                                        </tbody>\n' +
                '                                    </table>\n' +
                '                                </td>\n' +
                '                            </tr>\n' +
                '                            </tbody>\n' +
                '                        </table>\n' +
                '                    </td>\n' +
                '                </tr>\n' +
                '\n' +
                '                </tbody>\n' +
                '            </table>';
            $("#content").html(html);
        } else {
            alert(result.message);
        }
    });



} else {
    /*右边内容*/
    $.getJSON(BaseUrl + 'cms/webPage/menuPage', {menuId: menuId}, function (result) {
        console.log(result);
        if (result.code === 200) {
            $("#content").append(result.data.content);
        } else {
            alert(result.message);
        }
    });
}

