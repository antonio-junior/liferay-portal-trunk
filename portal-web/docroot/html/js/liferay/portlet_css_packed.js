Liferay.PortletCSS={init:function(I){var L=this;var F="p_p_id_"+I+"_";var E=jQuery("#"+F);var K=1;L._portletId=I;L._curPortlet=E.find(".portlet");L._curPortletWrapperId=L._curPortlet.attr("id");L._portletBoundaryId=F;L._newPanel=jQuery("#portlet-set-properties");L._currentLanguage=themeDisplay.getLanguageId();if(!L._curPortlet.length){L._curPortlet=E;L._curPortletWrapperId=F}var D=L._newPanel;if(L._curPortlet.length){if(!L._newPanel.is(".instantiated")){L._newPanel.addClass("instantiated");L._portletBoundaryIdVar=jQuery("#portlet-boundary-id");L._customTitleInput=jQuery("#custom-title");L._defaultPortletTitle=L._curPortlet.find(".portlet-title").eq(0).text();L._customTitleCheckbox=jQuery("#use-custom-title-checkbox");L._showBorders=jQuery("#show-borders");L._borderNote=jQuery("#border-note");L._portletLanguage=jQuery("#lfr-portlet-language");L._portletLinksTarget=jQuery("#lfr-point-links");L._fontFamily=jQuery("#lfr-font-family");L._fontWeight=jQuery("#lfr-font-bold");L._fontStyle=jQuery("#lfr-font-italic");L._fontSize=jQuery("#lfr-font-size");L._fontColor=jQuery("#lfr-font-color");L._textAlign=jQuery("#lfr-font-align");L._textDecoration=jQuery("#lfr-font-decoration");L._wordSpacing=jQuery("#lfr-font-space");L._leading=jQuery("#lfr-font-leading");L._tracking=jQuery("#lfr-font-tracking");L._backgroundColor=jQuery("#lfr-bg-color");L._useBgImage=jQuery("#lfr-use-bg-image");L._bgImageProperties=jQuery(".lfr-bg-image-properties");L._bgRepeating=jQuery("#lfr-bg-repeat");L._bgPosTop=jQuery("#lfr-bg-top-int");L._bgPosTopUnit=jQuery("#lfr-bg-top-unit");L._bgPosLeft=jQuery("#lfr-bg-left-int");L._bgPosLeftUnit=jQuery("#lfr-bg-left-unit");L._ufaBorderWidth=jQuery("#lfr-use-for-all-width");L._ufaBorderStyle=jQuery("#lfr-use-for-all-style");L._ufaBorderColor=jQuery("#lfr-use-for-all-color");L._borderTopInt=jQuery("#lfr-border-width-top");L._borderTopUnit=jQuery("#lfr-border-width-top-unit");L._borderRightInt=jQuery("#lfr-border-width-right");L._borderRightUnit=jQuery("#lfr-border-width-right-unit");L._borderBottomInt=jQuery("#lfr-border-width-bottom");L._borderBottomUnit=jQuery("#lfr-border-width-bottom-unit");L._borderLeftInt=jQuery("#lfr-border-width-left");L._borderLeftUnit=jQuery("#lfr-border-width-left-unit");L._borderTopStyle=jQuery("#lfr-border-style-top");L._borderRightStyle=jQuery("#lfr-border-style-right");L._borderBottomStyle=jQuery("#lfr-border-style-bottom");L._borderLeftStyle=jQuery("#lfr-border-style-left");L._borderTopColor=jQuery("#lfr-border-color-top");L._borderRightColor=jQuery("#lfr-border-color-right");L._borderBottomColor=jQuery("#lfr-border-color-bottom");L._borderLeftColor=jQuery("#lfr-border-color-left");L._ufaPadding=jQuery("#lfr-use-for-all-padding");L._ufaMargin=jQuery("#lfr-use-for-all-margin");L._paddingTopInt=jQuery("#lfr-padding-top");L._paddingTopUnit=jQuery("#lfr-padding-top-unit");L._paddingRightInt=jQuery("#lfr-padding-right");L._paddingRightUnit=jQuery("#lfr-padding-right-unit");L._paddingBottomInt=jQuery("#lfr-padding-bottom");L._paddingBottomUnit=jQuery("#lfr-padding-bottom-unit");L._paddingLeftInt=jQuery("#lfr-padding-left");L._paddingLeftUnit=jQuery("#lfr-padding-left-unit");L._marginTopInt=jQuery("#lfr-margin-top");L._marginTopUnit=jQuery("#lfr-margin-top-unit");L._marginRightInt=jQuery("#lfr-margin-right");L._marginRightUnit=jQuery("#lfr-margin-right-unit");L._marginBottomInt=jQuery("#lfr-margin-bottom");L._marginBottomUnit=jQuery("#lfr-margin-bottom-unit");L._marginLeftInt=jQuery("#lfr-margin-left");L._marginLeftUnit=jQuery("#lfr-margin-left-unit");L._customCSS=jQuery("#lfr-custom-css");L._saveButton=jQuery("#lfr-lookfeel-save");L._resetButton=jQuery("#lfr-lookfeel-reset");L._wapInitWindowStateSelect=jQuery("#lfr-wap-initial-window-state");L._wapTitleInput=jQuery("#lfr-wap-portlet-title");D.show();D.tabs({tabStruct:"form>fieldset",selectedClass:"current"});L._currentPopup=Liferay.Popup({width:800,message:D[0],modal:false,noCenter:true,onClose:function(){L._newPanel.removeClass("instantiated");jQuery(D[0]).hide().appendTo("body");if(Liferay.Browser.is_ie_6){window.location.reload(true)}}})}D.find(".lfr-color-picker-img").remove();L._portletMsgResponse=jQuery("#lfr-portlet-css-response");L._portletMsgResponse.hide();var C={advancedData:{customCSS:""},bgData:{backgroundColor:"",backgroundImage:"",useBgImage:false,backgroundRepeat:"",backgroundPosition:{left:{value:"",unit:"px"},top:{value:"",unit:"px"}}},borderData:{borderWidth:{bottom:{value:"",unit:"px"},left:{value:"",unit:"px"},right:{value:"",unit:"px"},top:{value:"",unit:"px"},sameForAll:true},borderStyle:{bottom:"",left:"",right:"",top:"",sameForAll:true},borderColor:{bottom:"",left:"",right:"",top:"",sameForAll:true}},portletData:{language:"en_US",portletLinksTarget:"",showBorders:true,title:"",titles:{},useCustomTitle:false},spacingData:{margin:{bottom:{value:"",unit:"px"},left:{value:"",unit:"px"},right:{value:"",unit:"px"},top:{value:"",unit:"px"},sameForAll:true},padding:{bottom:{value:"",unit:"px"},left:{value:"",unit:"px"},right:{value:"",unit:"px"},top:{value:"",unit:"px"},sameForAll:true}},textData:{textAlign:"",color:"",fontFamily:"",fontSize:"",fontStyle:"",fontWeight:"",letterSpacing:"",lineHeight:"",textDecoration:"",wordSpacing:""},wapData:{title:"",windowState:""}};var J=jQuery.ajax({url:themeDisplay.getPathMain()+"/portlet_configuration/get_look_and_feel",data:{p_l_id:themeDisplay.getPlid(),doAsUserId:themeDisplay.getDoAsUserIdEncoded(),portletId:L._portletId},async:false,dataType:"json",type:"POST"});if(J.responseText.length){J=jQuery.parseJSON(J.responseText);L._objData=J}else{L._objData=C}L._assignColorPickers();L._portletBoundaryIdVar.val(F);L._setDefaults();L._portletConfig();L._textStyles();L._backgroundStyles();L._borderStyles();L._spacingStyles();L._cssStyles();var A=D.find("li.current");D.triggerTab(K);if(A.length>1){A.not(":first").removeClass("current")}var H=D.find(".lfr-use-for-all");var G=function(){var N=jQuery(this);var O=N.parents("fieldset:first").find(".ctrl-holder:gt(1)");var P=O.find("input, select");var M=O.find(".lfr-color-picker-img");if(this.checked){O.fadeTo("fast",0.3);P.attr("disabled",true);M.hide()}else{O.fadeTo("fast",1);P.attr("disabled",false);M.show()}};H.unbind("click",G).click(G);H.each(G);var B=function(R,O){var Q=L._portletMsgResponse;var N="<div id=\"lfr-portlet-css-response\"></div>";var P="";var M="";if(O=="success"){P=Liferay.Language.get("your-request-processed-successfully");M="portlet-msg-success"}else{P=Liferay.Language.get("your-settings-could-not-be-saved");M="portlet-msg-error"}if(!Q.length){ajaxResponse=jQuery(N);L._newPanel.find("form").prepend(ajaxResponse);L._portletMsgResponse=ajaxResponse}ajaxResponse.hide();ajaxResponse.attr("class",M);ajaxResponse.empty();ajaxResponse.html(P);ajaxResponse.fadeIn("normal")};L._saveButton.unbind().click(function(){L._objData.advancedData.customCSS=L._customCSS.val();L._objData.wapData.title=L._wapTitleInput.val();L._objData.wapData.windowState=L._wapInitWindowStateSelect.val();jQuery.ajax({url:themeDisplay.getPathMain()+"/portlet_configuration/update_look_and_feel",data:{p_l_id:themeDisplay.getPlid(),doAsUserId:themeDisplay.getDoAsUserIdEncoded(),portletId:L._portletId,css:jQuery.toJSON(L._objData)},complete:B,type:"POST"})});L._resetButton.unbind().click(function(){L._curPortlet.attr("style","");jQuery("#lfr-custom-css-block-"+L._curPortletWrapperId).remove();L._objData=C;L._setDefaults()});Liferay.Util.addInputFocus(L._newPanel[0]);jQuery(L._currentPopup).parents(".popup").ScrollTo(800)}},_assignColorPickers:function(){var A=this;A._newPanel.find(".use-color-picker").each(function(){new Liferay.ColorPicker({context:jQuery("#portlet-set-properties")[0],item:this})})},_backgroundStyles:function(){var K=this;var M=K._objData.bgData;var J=K._curPortlet;var I=K._backgroundColor;var H=function(R){var P=R.value;var Q=P;if((P=="")||(P=="#")){Q="transparent";P=""}J.css("background-color",Q);M.backgroundColor=P};var B=new Liferay.ColorPicker({context:K._newPanel[0],item:I[0],onChange:function(){H(I[0])}});I.unbind().blur(function(){H(this)});var D=K._useBgImage;var E=D.is(":checked");var G=K._bgImageProperties;M.useBgImage=E;if(E){G.show()}D.unbind().click(function(){G.toggle();M.useBgImage=this.checked});var C=K._bgRepeating;C.unbind().change(function(){var P=this.options[this.selectedIndex].value;J.css("background-repeat",P);M.backgroundRepeat=P});var O=K._bgPosTop;var A=K._bgPosTopUnit;var L=K._bgPosLeft;var F=K._bgPosLeftUnit;var N=function(){var P=K._getCombo(O,A);var Q=K._getCombo(L,F);J.css("background-position",Q.both+" "+P.both);M.backgroundPosition.top.value=P.input;M.backgroundPosition.top.unit=P.selectBox;M.backgroundPosition.left.value=Q.input;M.backgroundPosition.left.unit=Q.selectBox};O.unbind().blur(N);L.unbind().blur(N);O.unbind().keyup(N);L.unbind().keyup(N);A.unbind().change(N);F.unbind().change(N)},_borderStyles:function(){var W=this;var S=W._curPortlet;var O=W._ufaBorderWidth;var T=W._ufaBorderStyle;var Q=W._ufaBorderColor;var X=W._objData.borderData;var M=W._borderTopInt;var L=W._borderTopUnit;var G=W._borderRightInt;var J=W._borderRightUnit;var E=W._borderBottomInt;var U=W._borderBottomUnit;var b=W._borderLeftInt;var K=W._borderLeftUnit;var V=function(){var g={};var f={};f=W._getCombo(M,L);g={borderWidth:f.both};var d=O.is(":checked");X.borderWidth.top.value=f.input;X.borderWidth.top.unit=f.selectBox;X.borderWidth.sameForAll=d;if(!d){var j={};j.borderTopWidth=g.borderWidth;var h=W._getCombo(G,J);var e=W._getCombo(E,U);var i=W._getCombo(b,K);j.borderRightWidth=h.both;j.borderBottomWidth=e.both;j.borderLeftWidth=i.both;g=j;X.borderWidth.right.value=h.input;X.borderWidth.right.unit=h.selectBox;X.borderWidth.bottom.value=e.input;X.borderWidth.bottom.unit=e.selectBox;X.borderWidth.left.value=i.input;X.borderWidth.left.unit=i.selectBox}S.css(g);c();Y()};M.unbind().blur(V);M.unbind().keyup(V);G.unbind().blur(V);G.unbind().keyup(V);E.unbind().blur(V);E.unbind().keyup(V);b.unbind().blur(V);b.unbind().keyup(V);L.unbind().change(V);J.unbind().change(V);U.unbind().change(V);K.unbind().change(V);O.unbind().click(V);var Z=W._borderTopStyle;var A=W._borderRightStyle;var P=W._borderBottomStyle;var F=W._borderLeftStyle;var c=function(){var g={};var f={};f=Z.find("option:selected").val();g={borderStyle:f};var d=T.is(":checked");X.borderStyle.top=f;X.borderStyle.sameForAll=d;if(!d){var j={};j.borderTopStyle=g.borderStyle;var h=A.find("option:selected").val();var e=P.find("option:selected").val();var i=F.find("option:selected").val();j.borderRightStyle=h;j.borderBottomStyle=e;j.borderLeftStyle=i;g=j;X.borderStyle.right=h;X.borderStyle.bottom=e;X.borderStyle.left=i}S.css(g)};Z.unbind().change(c);A.unbind().change(c);P.unbind().change(c);F.unbind().change(c);T.unbind().click(c);var B=W._borderTopColor;var N=W._borderRightColor;var H=W._borderBottomColor;var D=W._borderLeftColor;var Y=function(){var f={};var j={};j=B.val();f={borderColor:j};var d=Q.is(":checked");X.borderColor.top=j;X.borderColor.sameForAll=d;if(!d){var i={};i.borderTopColor=f.borderColor;var g=N.val();var e=H.val();var h=D.val();i.borderRightColor=g;i.borderBottomColor=e;i.borderLeftColor=h;f=i;X.borderColor.right=g;X.borderColor.bottom=e;X.borderColor.left=h}S.css(f)};var R=new Liferay.ColorPicker({context:jQuery("#portlet-set-properties")[0],item:B[0],onChange:Y});var I=new Liferay.ColorPicker({context:jQuery("#portlet-set-properties")[0],item:N[0],onChange:Y});var C=new Liferay.ColorPicker({context:jQuery("#portlet-set-properties")[0],item:H[0],onChange:Y});var a=new Liferay.ColorPicker({context:jQuery("#portlet-set-properties")[0],item:D[0],onChange:Y});B.unbind().blur(Y);N.unbind().blur(Y);H.unbind().blur(Y);D.unbind().blur(Y);B.unbind().keyup(Y);N.unbind().keyup(Y);H.unbind().keyup(Y);D.unbind().keyup(Y);Q.unbind().click(Y)},_cssStyles:function(){var R=this;var M=R._curPortlet;var L=jQuery("#lfr-custom-css");var J=L.parents(".ctrl-holder");var I="<p class=\"portlet-msg-info form-hint\"></p>";var C=jQuery("#lfr-portlet-info");var N="";var P=R._curPortletWrapperId;var D=M.attr("class");D=jQuery.trim(D).replace(/(\s)/g,"$1.");var Q=Liferay.Language.get("your-current-portlet-information-is-as-follows")+":<br />"+Liferay.Language.get("portlet-id")+": <strong>#"+P+"</strong><br />"+Liferay.Language.get("portlet-classes")+": <strong>."+D+"</strong>";var S=jQuery("#lfr-refresh-styles");if(!S.length){S=jQuery(I);S.attr({"class":"",id:"lfr-refresh-styles"})}if(!C.length){C=jQuery(I);J.before(C);C.attr({id:"lfr-portlet-info"})}C.html(Q);L.EnableTabs();if(!Liferay.Browser.is_safari){N=Liferay.Language.get("update-the-styles-on-this-page");var G=jQuery("<a href=\"javascript:;\">"+N+"</a>");var H=jQuery("#lfr-custom-css-block-"+P);if(!H.length){var K=document.createElement("style");K.id="lfr-custom-css-block-"+P;K.className="lfr-custom-css-block";K.setAttribute("type","text/css");document.getElementsByTagName("head")[0].appendChild(K)}else{K=H[0]}var U=function(){var V=L.val();V=V.replace(/<script[^>]*>([\u0001-\uFFFF]*?)<\/script>/gim,"");V=V.replace(/<\/?[^>]+>/gi,"");if(K.styleSheet){if(V==""){V="<!---->"}K.styleSheet.cssText=V}else{jQuery(K).html(V)}};G.unbind().click(U);S.empty().append(G)}else{N=Liferay.Language.get("please-press-the-save-button-to-view-your-changes");S.empty().text(N)}var B=jQuery("#lfr-add-rule-container");var O=jQuery("#lfr-add-id");var F=jQuery("#lfr-add-class");var E=jQuery("#lfr-update-on-type");if(!B.length){B=jQuery("<div id=\"lfr-add-rule-container\"></div>");O=jQuery("<a href=\"javascript:;\" id=\"lfr-add-id\">"+Liferay.Language.get("add-a-css-rule-for-just-this-portlet")+"</a>");F=jQuery("<a href=\"javascript:;\" id=\"lfr-add-class\">"+Liferay.Language.get("add-a-css-rule-for-all-portlets-like-this-one")+"</a>");var T=jQuery("<div class=\"ctrl-holder\"></div>");var A=jQuery("<label>"+Liferay.Language.get("update-my-styles-as-i-type")+" </label>");E=jQuery("<input id=\"lfr-update-on-type\" type=\"checkbox\" />");A.append(E);T.append(A);J.after(B);B.append(O);B.append("<br />");B.append(F);B.append(T);B.after(S)}E.click(function(){if(this.checked){S.hide();L.keyup(U)}else{S.show();L.unbind("keyup",U)}});O.unbind().click(function(){L[0].value+="\n#"+P+"{\n\t\n}\n"});F.unbind().click(function(){L[0].value+="\n."+D.replace(/\s/g,"")+"{\n\t\n}\n"})},_getCombo:function(C,E){var B=this;var D=C.val();var A=E.find("option:selected").val();D=B._getSafeInteger(D);return{input:D,selectBox:A,both:D+A}},_getSafeInteger:function(C){var A=this;var B=parseInt(C);if(B==""||isNaN(B)){B=0}return B},_languageClasses:function(B,D,E){var A=this;var C=A._portletLanguage.find("option[@value="+B+"]");if(E){C.removeClass("focused")}else{C.addClass("focused")}},_portletConfig:function(){var B=this;var F=B._objData.portletData;var D=B._customTitleInput;var H=B._customTitleCheckbox;var A=B._showBorders;var G=B._portletLanguage;var E=B._borderNote;var C=B._portletLinksTarget;H.unbind().click(function(){var I;F.useCustomTitle=this.checked;if(this.checked){D.attr("disabled",false);G.attr("disabled",false);I=jQuery.trim(D.val());if(I==""){I=B._curPortlet.find(".portlet-title").eq(0).text();I=jQuery.trim(I);D.val(I)}F.title=I;B._portletTitles(false,I)}else{D.attr("disabled",true);G.attr("disabled",true);I=B._defaultPortletTitle}B._curPortlet.find(".portlet-title").eq(0).text(I)});D.unbind().keyup(function(){if(!F.useCustomTitle||B._portletLanguage.find("option:selected").val()!=B._currentLanguage){return }B._curPortlet.find(".portlet-title").eq(0).text(this.value);F.title=this.value;B._portletTitles(false,this.value)});A.unbind().click(function(){E.toggle();F.showBorders=this.checked});G.change(function(){F.language=this.options[this.selectedIndex].value;var I=B._portletTitles(F.language);if(F.useCustomTitle){D.val(I)}});C.change(function(){F.portletLinksTarget=B._getSafeInteger(this.options[this.selectedIndex].value)})},_portletTitles:function(C,F){var B=this;var E=B._portletLanguage;if(!B._objData.portletData.titles){B._objData.portletData.titles={}}var D=B._objData.portletData.titles;if(!C){C=B._portletLanguage.find("option:selected").val()}if(F==null){var A=D[C];if(A){return A}return""}else{D[C]=F;if(F==""){B._languageClasses(C,null,true)}else{B._languageClasses(C)}}},_setCheckbox:function(C,B){var A=this;C.attr("checked",B)},_setDefaults:function(){var K=this;var N=K._objData;var A=N.portletData;var D=N.textData;var M=N.bgData;var H=N.borderData;var G=N.spacingData;var B=N.wapData||{title:"",windowState:""};N.wapData=B;var J=A.titles;var E=K._portletTitles(A.language);var C=false;var F=false;if(D.fontStyle!="normal"){C=true}if(D.fontWeight!="normal"){F=true}K._setInput(K._customTitleInput,E);K._setCheckbox(K._customTitleCheckbox,A.useCustomTitle);K._setCheckbox(K._showBorders,A.showBorders);K._setSelect(K._portletLanguage,K._currentLanguage);K._setSelect(K._portletLinksTarget,A.portletLinksTarget);if(!A.useCustomTitle){K._customTitleInput.attr("disabled",true);K._portletLanguage.attr("disabled",true)}if(A.titles){jQuery.each(A.titles,function(O,P){K._languageClasses(O)})}K._setSelect(K._fontFamily,D.fontFamily);K._setCheckbox(K._fontWeight,F);K._setCheckbox(K._fontStyle,C);K._setSelect(K._fontSize,D.fontSize);K._setInput(K._fontColor,D.color);K._setSelect(K._textAlign,D.textAlign);K._setSelect(K._textDecoration,D.textDecoration);K._setSelect(K._wordSpacing,D.wordSpacing);K._setSelect(K._leading,D.lineHeight);K._setSelect(K._tracking,D.letterSpacing);K._setInput(K._backgroundColor,M.backgroundColor);K._setCheckbox(K._useBgImage,M.useBgImage);K._setSelect(K._bgRepeating,M.backgroundRepeat);K._setInput(K._bgPosTop,M.backgroundPosition.top.value);K._setSelect(K._bgPosTopUnit,M.backgroundPosition.top.unit);K._setInput(K._bgPosLeft,M.backgroundPosition.left.value);K._setSelect(K._bgPosLeftUnit,M.backgroundPosition.left.unit);K._setCheckbox(K._ufaBorderWidth,H.borderWidth.sameForAll);K._setCheckbox(K._ufaBorderStyle,H.borderStyle.sameForAll);K._setCheckbox(K._ufaBorderColor,H.borderColor.sameForAll);K._setInput(K._borderTopInt,H.borderWidth.top.value);K._setSelect(K._borderTopUnit,H.borderWidth.top.unit);K._setInput(K._borderRightInt,H.borderWidth.right.value);K._setSelect(K._borderRightUnit,H.borderWidth.right.unit);K._setInput(K._borderBottomInt,H.borderWidth.bottom.value);K._setSelect(K._borderBottomUnit,H.borderWidth.bottom.unit);K._setInput(K._borderLeftInt,H.borderWidth.left.value);K._setSelect(K._borderLeftUnit,H.borderWidth.left.unit);K._setSelect(K._borderTopStyle,H.borderStyle.top);K._setSelect(K._borderRightStyle,H.borderStyle.right);K._setSelect(K._borderBottomStyle,H.borderStyle.bottom);K._setSelect(K._borderLeftStyle,H.borderStyle.left);K._setInput(K._borderTopColor,H.borderColor.top);K._setInput(K._borderRightColor,H.borderColor.right);K._setInput(K._borderBottomColor,H.borderColor.bottom);K._setInput(K._borderLeftColor,H.borderColor.left);K._setCheckbox(K._ufaPadding,G.padding.sameForAll);K._setCheckbox(K._ufaMargin,G.margin.sameForAll);K._setInput(K._paddingTopInt,G.padding.top.value);K._setSelect(K._paddingTopUnit,G.padding.top.unit);K._setInput(K._paddingRightInt,G.padding.right.value);K._setSelect(K._paddingRightUnit,G.padding.right.unit);K._setInput(K._paddingBottomInt,G.padding.bottom.value);K._setSelect(K._paddingBottomUnit,G.padding.bottom.unit);K._setInput(K._paddingLeftInt,G.padding.left.value);K._setSelect(K._paddingLeftUnit,G.padding.left.unit);K._setInput(K._marginTopInt,G.margin.top.value);K._setSelect(K._marginTopUnit,G.margin.top.unit);K._setInput(K._marginRightInt,G.margin.right.value);K._setSelect(K._marginRightUnit,G.margin.right.unit);K._setInput(K._marginBottomInt,G.margin.bottom.value);K._setSelect(K._marginBottomUnit,G.margin.bottom.unit);K._setInput(K._marginLeftInt,G.margin.left.value);K._setSelect(K._marginLeftUnit,G.margin.left.unit);var L=jQuery("#lfr-custom-css-block-"+K._curPortletWrapperId);var I=L.html();if(I==""||I==null){I=N.advancedData.customCSS}K._setTextarea(K._customCSS,I);K._setInput(K._wapTitleInput,B.title);K._setSelect(K._wapInitWindowStateSelect,B.windowState)},_setInput:function(C,B){var A=this;C.val(B)},_setSelect:function(C,B){var A=this;if(B!=""){C.find("option[@value="+B+"]").attr("selected","selected")}},_setTextarea:function(C,B){var A=this;A._setInput(C,B)},_spacingStyles:function(){var O=this;var L=O._curPortlet;var V=O._ufaPadding;var U=O._ufaMargin;var R=O._objData.spacingData;var I=O._paddingTopInt;var W=O._paddingTopUnit;var Q=O._paddingRightInt;var K=O._paddingRightUnit;var A=O._paddingBottomInt;var T=O._paddingBottomUnit;var H=O._paddingLeftInt;var J=O._paddingLeftUnit;var N=function(){var Z={};var c=O._getCombo(I,W);Z={padding:c.both};var X=V.is(":checked");R.padding.top.value=c.input;R.padding.top.unit=c.selectBox;R.padding.sameForAll=X;if(!X){var d={};d.paddingTop=Z.padding;var a=O._getCombo(Q,K);var Y=O._getCombo(A,T);var b=O._getCombo(H,J);d.paddingRight=a.both;d.paddingBottom=Y.both;d.paddingLeft=b.both;Z=d;R.padding.right.value=a.input;R.padding.right.unit=a.selectBox;R.padding.bottom.value=Y.input;R.padding.bottom.unit=Y.selectBox;R.padding.left.value=b.input;R.padding.left.unit=b.selectBox}L.css(Z)};I.unbind().blur(N);Q.unbind().blur(N);A.unbind().blur(N);H.unbind().blur(N);I.unbind().keyup(N);Q.unbind().keyup(N);A.unbind().keyup(N);H.unbind().keyup(N);W.unbind().change(N);K.unbind().change(N);T.unbind().change(N);J.unbind().change(N);V.unbind().click(N);var C=O._marginTopInt;var G=O._marginTopUnit;var F=O._marginRightInt;var P=O._marginRightUnit;var E=O._marginBottomInt;var S=O._marginBottomUnit;var M=O._marginLeftInt;var D=O._marginLeftUnit;var B=function(){var Z={};var b=O._getCombo(C,G);Z={margin:b.both};var X=U.is(":checked");R.margin.top.value=b.input;R.margin.top.unit=b.selectBox;R.margin.sameForAll=X;if(!X){var d={};d.marginTop=Z.margin;var a=O._getCombo(F,P);var Y=O._getCombo(E,S);var c=O._getCombo(M,D);d.marginRight=a.both;d.marginBottom=Y.both;d.marginLeft=c.both;Z=d;R.margin.right.value=a.input;R.margin.right.unit=a.selectBox;R.margin.bottom.value=Y.input;R.margin.bottom.unit=Y.selectBox;R.margin.left.value=c.input;R.margin.left.unit=c.selectBox}L.css(Z)};C.unbind().blur(B);F.unbind().blur(B);E.unbind().blur(B);M.unbind().blur(B);C.unbind().keyup(B);F.unbind().keyup(B);E.unbind().keyup(B);M.unbind().keyup(B);G.unbind().change(B);P.unbind().change(B);S.unbind().change(B);D.unbind().change(B);U.unbind().click(B)},_textStyles:function(){var M=this;var L=M._curPortlet;var I=M._fontFamily;var G=M._fontWeight;var J=M._fontStyle;var O=M._fontSize;var H=M._fontColor;var C=M._textAlign;var N=M._textDecoration;var B=M._wordSpacing;var D=M._leading;var E=M._tracking;var F=M._objData.textData;I.unbind().change(function(){var P=this.options[this.selectedIndex].value;L.css("font-family",P);F.fontFamily=P});G.unbind().click(function(){var P="normal";if(this.checked){P="bold"}L.css("font-weight",P);F.fontWeight=P});J.unbind().click(function(){var P="normal";if(this.checked){P="italic"}L.css("font-style",P);F.fontStyle=P});O.unbind().change(function(){var P=this.options[this.selectedIndex].value;L.css("font-size",P);F.fontSize=P});var K=function(Q){var P=Q.value;if(P!=""){L.css("color",P);F.color=P}};var A=new Liferay.ColorPicker({context:jQuery("#portlet-set-properties")[0],item:H[0],onChange:function(){K(H[0])}});H.unbind().blur(function(){K(this)});C.unbind().change(function(){var P=this.options[this.selectedIndex].value;L.css("text-align",P);F.textAlign=P});N.unbind().change(function(){var P=this.options[this.selectedIndex].value;L.css("text-decoration",P);F.textDecoration=P});B.unbind().change(function(){var P=this.options[this.selectedIndex].value;L.css("word-spacing",P);F.wordSpacing=P});D.unbind().change(function(){var P=this.options[this.selectedIndex].value;L.css("line-height",P);F.lineHeight=P});E.unbind().change(function(){var P=this.options[this.selectedIndex].value;L.css("letter-spacing",P);F.letterSpacing=P})}}