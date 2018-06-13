function getElementsByName_iefix(tag, name) {
var elem = document.getElementsByTagName(tag);
var arr = new Array();
for(i = 0,iarr = 0; i < elem.length; i++) {
 att = elem[i].getAttribute("name");
 if(att == name) {
    arr[iarr] = elem[i];
    iarr++;
 }
}
return arr;
}

function doZoom(size)
{
var zooms = getElementsByName_iefix("span", "zoom")
for (var i = 0; i < zooms.length; i++) {
  zooms[i].style.fontSize = size+'px';
}
}

