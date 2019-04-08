function ajaxRequest(method,url,handler,dataType,param){
	console.log("要请求的url:"+url);
	//1.创建XMLHttpRequest对象
    console.log("param:"+param);
    console.log("创建XMLHttpRequest对象  ");
	var xmlHttp;
	if(window.XMLHttpRequest){
		xmlHttp = new XMLHttpRequest();
        console.log("创建第一种   ");
	}else{
		xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
        console.log("创建第二种   ");
	}
	//2.设置回调函数，设置监听。
	xmlHttp.onreadystatechange = function(){
        console.log("进入回调函数");
		if(xmlHttp.readyState==4&&xmlHttp.status==200){
            console.log("进入状态");
			//处理过程，应该由用户指定。
			var data;
			if("XML"==dataType){
				data = xmlHttp.responseXML;
                console.log("接收到的XML数据："+data);
			}else{
				data = xmlHttp.responseText;
                console.log("接收到的text数据："+data);
			}
			handler(data);
		}
	};
	//3.创建请求
    console.log("要创建请求  ");
	xmlHttp.open(method, url,true);
    console.log("创建请求完成  ");
	//4.发送请求
	if(method=="POST"&&param){
        console.log("准备发送有参的  ");
		xmlHttp.setRequestHeader("Content-type",
				"application/x-www-form-urlencoded");
		console.log("参数封装前:"+param);
		var param = encapsulationParam(param);
		console.log("最终参数:"+param);
		xmlHttp.send(param);
	}else{
        console.log("准备发送无参的请求  ");
		xmlHttp.send();
	}
}
function encapsulationParam(param){
        var param = param.replace("@#$","&");
        param = param.replace("@#$","&");
		param = param.replace("@#$","&");
		param = param.replace("@#$","&");
		param = param.replace("@#$","&");
		param = param.replace("@#$","&");
		param = param.replace("@#$","&");
		param = param.replace("@#$","&");
		param = param.replace("@#$","&");
		param = param.replace("@#$","&");
		param = param.replace("@#$","&");
		param = param.replace("@#$","&");
		param = param.replace("@#$","&");
		param = param.replace("@#$","&");
		param = param.replace("@#$","&");
		param = param.replace("@#$","&");
		param = param.replace("@#$","&");
		param = param.replace("@#$","&");
		param = param.replace("@#$","&");
		param = param.replace("@#$","&");
		param = param.replace("@#$","&");
        return param;
}




