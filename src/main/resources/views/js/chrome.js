//Chrome Drop Down Menu v2.01- Author: Dynamic Drive (http://www.dynamicdrive.com)
//Last updated: November 14th 06- added iframe shim technique

var cssdropdown={
disappeardelay: 250, //set delay in miliseconds before menu disappears onmouseout
disablemenuclick: false, //when user clicks on a menu item with a drop down menu, disable menu item's link?
enableswipe: 1, //enable swipe effect? 1 for yes, 0 for no
enableiframeshim: 1, //enable "iframe shim" technique to get drop down menus to correctly appear on top of controls such as form objects in IE5.5/IE6? 1 for yes, 0 for no
currentOver:null,

//No need to edit beyond here////////////////////////
dropmenuobj: null, ie: document.all, firefox: document.getElementById&&!document.all, swipetimer: undefined, bottomclip:0,

getposOffset:function(what, offsettype){
var totaloffset=(offsettype=="left")? what.offsetLeft : what.offsetTop;
var parentEl=what.offsetParent;
while (parentEl!=null){
totaloffset=(offsettype=="left")? totaloffset+parentEl.offsetLeft : totaloffset+parentEl.offsetTop;
parentEl=parentEl.offsetParent;
}
return totaloffset;
},

swipeeffect:function(){
if (this.bottomclip<parseInt(this.dropmenuobj.offsetHeight)){
this.bottomclip+=10+(this.bottomclip/10) //unclip drop down menu visibility gradually
this.dropmenuobj.style.clip="rect(0 auto "+this.bottomclip+"px 0)"
}
else
return
this.swipetimer=setTimeout("cssdropdown.swipeeffect()", 10)
},

showhide:function(obj, e){
if (this.ie || this.firefox)
this.dropmenuobj.style.left=this.dropmenuobj.style.top="-500px"
if (e.type=="click" && obj.visibility==hidden || e.type=="mouseover"){
if (this.enableswipe==1){
if (typeof this.swipetimer!="undefined")
clearTimeout(this.swipetimer)
obj.clip="rect(0 auto 0 0)" //hide menu via clipping
this.bottomclip=0
this.swipeeffect()
}
obj.visibility="visible"
}
else if (e.type=="click")
obj.visibility="hidden"
},

iecompattest:function(){
return (document.compatMode && document.compatMode!="BackCompat")? document.documentElement : document.body
},

clearbrowseredge:function(obj, whichedge){
var edgeoffset=0
if (whichedge=="rightedge"){
var windowedge=this.ie && !window.opera? this.iecompattest().scrollLeft+this.iecompattest().clientWidth-15 : window.pageXOffset+window.innerWidth-15
this.dropmenuobj.contentmeasure=this.dropmenuobj.offsetWidth
if (windowedge-this.dropmenuobj.x < this.dropmenuobj.contentmeasure)  //move menu to the left?
edgeoffset=this.dropmenuobj.contentmeasure-obj.offsetWidth
}
else{
var topedge=this.ie && !window.opera? this.iecompattest().scrollTop : window.pageYOffset
var windowedge=this.ie && !window.opera? this.iecompattest().scrollTop+this.iecompattest().clientHeight-15 : window.pageYOffset+window.innerHeight-18
this.dropmenuobj.contentmeasure=this.dropmenuobj.offsetHeight
if (windowedge-this.dropmenuobj.y < this.dropmenuobj.contentmeasure){ //move up?
edgeoffset=this.dropmenuobj.contentmeasure+obj.offsetHeight
if ((this.dropmenuobj.y-topedge)<this.dropmenuobj.contentmeasure) //up no good either?
edgeoffset=this.dropmenuobj.y+obj.offsetHeight-topedge
}
}
return edgeoffset
},

dropit:function(obj, e, dropmenuID){
if (this.dropmenuobj!=null) //hide previous menu
this.dropmenuobj.style.visibility="hidden" //hide menu
this.clearhidemenu()
if (this.ie||this.firefox){
obj.onmouseout=function(){cssdropdown.delayhidemenu(obj)}
obj.onclick=function(){return !cssdropdown.disablemenuclick} //disable main menu item link onclick?
this.dropmenuobj=document.getElementById(dropmenuID)
this.dropmenuobj.onmouseover=function(){cssdropdown.clearhidemenu()}
this.dropmenuobj.onmouseout=function(e){cssdropdown.dynamichide(e, obj)}
this.dropmenuobj.onclick=function(){cssdropdown.delayhidemenu(obj)}
this.showhide(this.dropmenuobj.style, e)
this.dropmenuobj.x=this.getposOffset(obj, "left")
this.dropmenuobj.y=this.getposOffset(obj, "top")
this.dropmenuobj.style.left=this.dropmenuobj.x-this.clearbrowseredge(obj, "rightedge")+"px"
this.dropmenuobj.style.top=this.dropmenuobj.y-this.clearbrowseredge(obj, "bottomedge")+obj.offsetHeight+"px"
this.positionshim() //call iframe shim function
}
},

positionshim:function(){ //display iframe shim function
if (this.enableiframeshim && typeof this.shimobject!="undefined"){
if (this.dropmenuobj.style.visibility=="visible"){
this.shimobject.style.width=this.dropmenuobj.offsetWidth+"px"
this.shimobject.style.height=this.dropmenuobj.offsetHeight+"px"
this.shimobject.style.left=this.dropmenuobj.style.left
this.shimobject.style.top=this.dropmenuobj.style.top
}
this.shimobject.style.display=(this.dropmenuobj.style.visibility=="visible")? "block" : "none"
}
},

hideshim:function(){
if (this.enableiframeshim && typeof this.shimobject!="undefined")
this.shimobject.style.display='none'
},

contains_firefox:function(a, b) {
while (b.parentNode)
if ((b = b.parentNode) == a)
return true;
return false;
},

dynamichide:function(e, obj){
var evtobj=window.event? window.event : e
if (this.ie&&!this.dropmenuobj.contains(evtobj.toElement))
this.delayhidemenu(obj)
else if (this.firefox&&e.currentTarget!= evtobj.relatedTarget&& !this.contains_firefox(evtobj.currentTarget, evtobj.relatedTarget))
this.delayhidemenu(obj)
},

delayhidemenu:function(obj){
this.delayhide=setTimeout(function(){cssdropdown.dropmenuobj.style.visibility='hidden'; cssdropdown.hideshim(); cssdropdown.restoreImgBg(obj); },this.disappeardelay) //hide menu
},

restoreImgBg:function(obj){
	if (obj == null)
		return 
	var img = obj.getElementsByTagName("img");
	for (var j=0; j<img.length; j++){
			//alert(img[j].src.replace(".gif","_o.gif"/*tpa=http://www.cethl.com/js/_o.gif*/));			
			if (img[j].src.indexOf("_o.gif"/*tpa=http://www.cethl.com/js/_o.gif*/)>=0)
				img[j].src = img[j].src.replace("_o.gif"/*tpa=http://www.cethl.com/js/_o.gif*/,".gif");
	}
},
overImgBg:function(obj){
	var img = obj.getElementsByTagName("img");
	for (var j=0; j<img.length; j++){
			//alert(img[j].src.replace(".gif","_o.gif"/*tpa=http://www.cethl.com/js/_o.gif*/));
			if (img[j].src.indexOf("_o.gif"/*tpa=http://www.cethl.com/js/_o.gif*/)<0)
				img[j].src = img[j].src.replace(".gif","_o.gif"/*tpa=http://www.cethl.com/js/_o.gif*/);			
	}
},

clearhidemenu:function(){
if (this.delayhide!="undefined")
clearTimeout(this.delayhide)
},

startchrome:function(){
	for (var ids=0; ids<arguments.length; ids++){
		var menuitems=document.getElementById(arguments[ids]).getElementsByTagName("a")
		for (var i=0; i<menuitems.length; i++){
			if (menuitems[i].getAttribute("rel")){
			var relvalue=menuitems[i].getAttribute("rel")
			
			menuitems[i].onmouseover=function(e){
				cssdropdown.restoreImgBg(cssdropdown.currentOver);
				cssdropdown.currentOver = this;				
				cssdropdown.overImgBg(this);
				var event=typeof e!="undefined"? e : window.event
				cssdropdown.dropit(this,event,this.getAttribute("rel"))
				}
			}
			}
			}
			if (window.createPopup && !window.XmlHttpRequest){ //if IE5.5 to IE6, create iframe for iframe shim technique
			document.write('<IFRAME id="iframeshim"  src="" style="display: none; left: 0; top: 0; z-index: 90; position: absolute; filter: progid:DXImageTransform.Microsoft.Alpha(style=0,opacity=0)" frameBorder="0" scrolling="no"></IFRAME>')
			this.shimobject=document.getElementById("iframeshim") //reference iframe object
		}
	}

}