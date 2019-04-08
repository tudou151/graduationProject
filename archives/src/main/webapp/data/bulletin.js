//更新公告面板中的内容
function update_bulletin_div(data) {
    document.getElementById('a_div_id_19').innerHTML=data;
    document.getElementById('top_div_1').style.display='none';
}
//查看详情
function look_bulletin_content(id,title,content,startDate,endDate) {
    document.getElementById('top_div_2').style.display='';
    document.getElementById(id).style.display='';
    document.getElementById('a_div_id_29').innerHTML=title;
    document.getElementById('a_div_id_30').innerHTML=content;
    document.getElementById('a_div_id_41').innerHTML=startDate;
    document.getElementById('a_div_id_42').innerHTML=endDate;
}

function close_bulletin_content_panel(id) {
    document.getElementById('top_div_2').style.display='none';
    document.getElementById(id).style.display='none';
}
//公告上一页
function bulletin_upPage(id,currentPageNum) {
    login_yesOrNo();
    document.getElementById('top_div_1').style.display='';
    //var currentPage = document.getElementById(id).value;
    currentPageNum--;
    ajaxRequest("GET",url+"/BulletinResource/getPage?currentPage="+currentPageNum,update_bulletin_div,"text","");
}
//公告  下一页
function bulletin_downPage(id,currentPageNum) {
    login_yesOrNo();
    document.getElementById('top_div_1').style.display='';
    //var currentPage = document.getElementById(id).value;
    ++currentPageNum;
    ajaxRequest("GET",url+"/BulletinResource/getPage?currentPage="+currentPageNum,update_bulletin_div,"text","");
}
//公告 跳转到指定页面
function bulletin_kipPage(id,pageNum,currentPageNum) {
    login_yesOrNo();
    var currentPage = document.getElementById(id).value;
    if(currentPage<=pageNum){
        ajaxRequest("GET",url+"/BulletinResource/getPage?currentPage="+currentPage,update_bulletin_div,"text","");
    }else{
        alert("请输入在页数范围内的数字");
        document.getElementById('a_div_id_26').value=currentPageNum;
    }
}

var bulletin_checkbox_id;
var bulletin_select_YesOrNo=0;
//公告选中或取消全部复选框
function bulletin_select_all(num) {
    var current_num=1;
    if (bulletin_select_YesOrNo==0){
        while(current_num<=num){
            bulletin_checkbox_id = "b_checkbox_"+current_num;
            document.getElementById(bulletin_checkbox_id).checked = true;
            current_num++;
        }
        bulletin_select_YesOrNo=1;
    }else{
        while(current_num<=num){
            bulletin_checkbox_id = "b_checkbox_"+current_num;
            document.getElementById(bulletin_checkbox_id).checked = false;
            current_num++;
        }
        bulletin_select_YesOrNo=0;
    }
}
//删除公告
function del_bulletin(num) {
    login_yesOrNo();
    document.getElementById('top_div_1').style.display='';
    var ids = new Array();
    var ids_num=1;
    var add_String_Array=0;
    while(ids_num<=num){
        checkbox_id = "b_checkbox_"+ids_num;
        if (document.getElementById(checkbox_id).checked){
            ids[add_String_Array]=""+document.getElementById(checkbox_id).value;
            add_String_Array++;
        }
        ids_num++;
    }

    if(ids.length!=0){
        if(confirm("确定删除？")){
            ajaxRequest("POST",url+"/BulletinResource/deleteBulletinResource",del_bulletin_handle,"text","ids="+ids.toString());
        }else{
            document.getElementById('top_div_1').style.display='none';
        }
    }else{
        document.getElementById('top_div_1').style.display='none';
        alert("请选择要删除的数据");
    }
}
//删除公告的结果处理
function del_bulletin_handle(data) {
    login_yesOrNo();
    document.getElementById('top_div_1').style.display='';
    data=eval("("+data+")");
    if (data['code']==0){
        alert("删除成功");
        ajaxRequest("GET",url+"/BulletinResource/getAllBulletinResources",update_bulletin_div,"text","");
    }else{
        alert(data['msg']);
    }
}

//根据名称查询公告
function selectbulletin_byBulletinTitle(inputId) {
    login_yesOrNo();
    var input = document.getElementById(inputId);
    var bulletintitle = input.bulletintitle.value;
    var param="bulletintitle="+bulletintitle;
    ajaxRequest("POST",url+"/BulletinResource/getIdByTitle",select_bulletin_handle,"text",param);
}

function select_bulletin_handle(data) {
    login_yesOrNo();
    document.getElementById('top_div_2').style.display="none";
    var data = eval("("+data+")");
    if (data['code']==0) {
        var id = data['data'];
        ajaxRequest("GET",url+"/BulletinResource/getBulletinResourceById?id="+id,update_bulletin_div,"text","");
    }else{
        document.getElementById('top_div_1').style.display="none";
        alert(data['msg']);
    }
}
//新增公告
function add_bulletin(id) {
    document.getElementById('top_div_2').style.display='';
    document.getElementById(id).style.display='';
}
//确定新增公告
function add_bulletin_true(formId) {
    login_yesOrNo();
    var form = document.getElementById(formId);
    var title = form.title.value;
    var startDate = form.startDate.value;
    var endDate = form.endDate.value;
    var content = form.content.value;
    if (title=="" || startDate=="" || endDate=="" || content==""){
        alert("请将信息填写完整");
    }else{
        var param="title="+title+"@#$startDate="+startDate+"@#$endDate="+endDate+"@#$content="+content;
        ajaxRequest("POST",url+"/BulletinResource/addBulletinResource",add_bulletin_handle,"text",param);
    }
}
//公告新增结果处理
function add_bulletin_handle(data) {
    login_yesOrNo();
    document.getElementById('top_div_2').style.display='none';
    document.getElementById('a_div_id_22').style.display='none';
    var data= eval("("+data+")");
    if(data['code']==0){
        alert(data['msg']);
        ajaxRequest("GET",url+"/page/returnBulletinJSP",update_bulletin_div,"text","");
    }else{
        alert(data['msg']);
    }
}
//选择要修改的公告
function modify_bulletin_btn(num) {
    login_yesOrNo();
    document.getElementById('top_div_1').style.display="";
    var ids_num1=1;
    var YN_greater_one=0;
    var id;
    while (ids_num1<=num){
        checkbox_id = "b_checkbox_"+ids_num1;
        if (document.getElementById(checkbox_id).checked){
            if (YN_greater_one==0){
                id = document.getElementById(checkbox_id).value;
                YN_greater_one++;
            }else{
                YN_greater_one=-1;
                break;
            }
        }
        ids_num1++;
    }
    if (YN_greater_one==1){
        ajaxRequest("GET",url+"/BulletinResource/getBulletinById?id="+id,show_modify_bulletin,"text","");
    }else{
        document.getElementById('top_div_1').style.display="none";
        alert("请选择一条数据进行修改");
    }
}
var modify_bulletin_id;
//展示要修改的公告信息
function show_modify_bulletin(data) {
    document.getElementById('top_div_1').style.display="none";
    var data = eval("("+data+")");
    if (data['code']==0){
        document.getElementById('top_div_2').style.display="";
        document.getElementById('a_div_id_25').style.display="";
        var datas=data['data'];
        modify_bulletin_id=datas['id'];
        var forms=document.getElementById('a_div_id_43');
        forms.title.value=datas['title'];
        forms.startDate.value=date_conversion(datas['startDate']);
        forms.endDate.value=date_conversion(datas['endDate']);
        forms.content.value=datas['content'];
    }else{
        alert(data['msg'])
    }
}

//时间转换
function date_conversion(time) {
    var date = new Date(time);
    var str = date.getFullYear() + "-" + fix((date.getMonth() + 1),2) + "-" + fix(date.getDate(),2) + "T" + fix(date.getHours(),2) + ":" + fix(date.getMinutes(),2);
    return str;
}
function fix(num, length) {
    return ('' + num).length < length ? ((new Array(length + 1)).join('0') + num).slice(-length) : '' + num;
}

function update_bulletin_true(id) {
    login_yesOrNo();
    document.getElementById('top_div_2').style.display="none";
    document.getElementById('a_div_id_25').style.display="none";
    document.getElementById('top_div_1').style.display="";
    var form=document.getElementById(id);
    var title = form.title.value;
    var startDate=form.startDate.value;
    var endDate=form.endDate.value;
    var content=form.content.value;
    var param="title="+title+"@#$startDate="+startDate+"@#$endDate="+endDate+"@#$content="+content+"@#$id="+modify_bulletin_id;
    ajaxRequest("POST",url+"/BulletinResource/modifyBulletinResource",update_bulletin_handle,"text",param);
}
//修改公告的处理结果
function update_bulletin_handle(data) {
    var data=eval("("+data+")");
    if(data['code']==0){
        document.getElementById('top_div_1').style.display="none";
        alert("修改成功");
        login_yesOrNo();
        ajaxRequest("GET",url+"/page/returnBulletinJSP",update_bulletin_div,"text","");
    }else{
        document.getElementById('top_div_1').style.display="none";
        alert(data['msg']);
    }
}