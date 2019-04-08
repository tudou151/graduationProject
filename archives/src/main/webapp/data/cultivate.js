//更新培训管理面板中的内容
function update_cultivate_div(data) {
    document.getElementById('f_div_id_19').innerHTML=data;
    document.getElementById('top_div_1').style.display='none';
}

function close_cultivate_content_panel(id) {
    document.getElementById('top_div_2').style.display='none';
    document.getElementById(id).style.display='none';
}
//培训管理上一页
function cultivate_upPhappenTime(id,currentPhappenTimeNum) {
    login_yesOrNo();
    document.getElementById('top_div_1').style.display='';
    //var currentPhappenTime = document.getElementById(id).value;
    currentPhappenTimeNum--;
    ajaxRequest("GET",url+"/Cultivate/getPhappenTime?currentPhappenTime="+currentPhappenTimeNum,update_cultivate_div,"text","");
}
//培训管理  下一页
function cultivate_downPhappenTime(id,currentPhappenTimeNum) {
    login_yesOrNo();
    document.getElementById('top_div_1').style.display='';
    //var currentPhappenTime = document.getElementById(id).value;
    ++currentPhappenTimeNum;
    ajaxRequest("GET",url+"/Cultivate/getPhappenTime?currentPhappenTime="+currentPhappenTimeNum,update_cultivate_div,"text","");
}
//培训管理 跳转到指定页面
function cultivate_kipPhappenTime(id,phappenTimeNum,currentPhappenTimeNum) {
    login_yesOrNo();
    var currentPhappenTime = document.getElementById(id).value;
    if(currentPhappenTime<=phappenTimeNum){
        ajaxRequest("GET",url+"/Cultivate/getPhappenTime?currentPhappenTime="+currentPhappenTime,update_cultivate_div,"text","");
    }else{
        alert("请输入在页数范围内的数字");
        document.getElementById('f_div_id_26').value=currentPhappenTimeNum;
    }
}

var cultivate_checkbox_id;
var cultivate_select_YesOrNo=0;
//培训管理选中或取消全部复选框
function cultivate_select_all(num) {
    var current_num=1;
    if (cultivate_select_YesOrNo==0){
        while(current_num<=num){
            cultivate_checkbox_id = "b_checkbox_"+current_num;
            document.getElementById(cultivate_checkbox_id).checked = true;
            current_num++;
        }
        cultivate_select_YesOrNo=1;
    }else{
        while(current_num<=num){
            cultivate_checkbox_id = "b_checkbox_"+current_num;
            document.getElementById(cultivate_checkbox_id).checked = false;
            current_num++;
        }
        cultivate_select_YesOrNo=0;
    }
}
//删除培训管理
function del_cultivate(num) {
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
            ajaxRequest("POST",url+"/Cultivate/deleteCultivate",del_cultivate_handle,"text","ids="+ids.toString());
        }else{
            document.getElementById('top_div_1').style.display='none';
        }
    }else{
        document.getElementById('top_div_1').style.display='none';
        alert("请选择要删除的数据");
    }
}
//删除培训管理的结果处理
function del_cultivate_handle(data) {
    login_yesOrNo();
    document.getElementById('top_div_1').style.display='';
    data=eval("("+data+")");
    if (data['code']==0){
        alert("删除成功");
        ajaxRequest("GET",url+"/Cultivate/getAllCultivates",update_cultivate_div,"text","");
    }else{
        alert(data['msg']);
    }
}

//根据姓名查询
function selectCultivate_byemployeename(inputId) {
    login_yesOrNo();
    var input = document.getElementById(inputId);
    var employeeName = input.employeeName.value;
    var param="employeeName="+employeeName;
    ajaxRequest("POST",url+"/Cultivate/getEmployeeNumberByEmployeeName",select_cultivate_handle,"text",param);
}

function select_cultivate_handle(data) {
    login_yesOrNo();
    document.getElementById('top_div_2').style.display="none";
    var data = eval("("+data+")");
    if (data['code']==0) {
        var employeeNumber = data['data'];
        ajaxRequest("GET",url+"/Cultivate/getCultivateByEmployeeNumber?employeeNumber="+employeeNumber,update_cultivate_div,"text","");
    }else{
        document.getElementById('top_div_1').style.display="none";
        alert(data['msg']);
    }
}
//新增培训管理
function add_cultivate(id) {
    document.getElementById('top_div_2').style.display='';
    document.getElementById(id).style.display='';
}

//确定培训管理
function add_cultivate_true(formId) {

    login_yesOrNo();
    var form = document.getElementById(formId);
    var address = form.address.value;
    var howLong = form.howLong.value;
    var happenTime = form.happenTime.value;
    var score = form.score.value;
    var content = form.content.value;
    var employeeNumber = form.employeeNumber.value;
    if (address=="" || employeeNumber=="" || howLong=="" || happenTime=="" || score=="" || content=="" ){
        alert("请将信息填写完整");
    }else{
        var param="address="+address+"@#$employeeNumber=" +employeeNumber+"@#$howLong="+howLong+"@#$happenTime="+happenTime+"@#$score="+score+"@#$content="+content;
        ajaxRequest("POST",url+"/Cultivate/addCultivate",add_cultivate_handle,"text",param);
    }
}
//培训管理新增结果处理
function add_cultivate_handle(data) {
    login_yesOrNo();
    document.getElementById('top_div_2').style.display='none';
    document.getElementById('f_div_id_22').style.display='none';
    var data= eval("("+data+")");
    if(data['code']==0){
        alert(data['msg']);
        ajaxRequest("GET",url+"/page/returnCultivateJSP",update_cultivate_div,"text","");
    }else{
        alert(data['msg']);
    }
}
//选择要修改的培训管理
function modify_cultivate_btn(num) {
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
        ajaxRequest("GET",url+"/Cultivate/getCultivateById?id="+id,show_modify_cultivate,"text","");
    }else{
        document.getElementById('top_div_1').style.display="none";
        alert("请选择一条数据进行修改");
    }
}
var modify_cultivate_id;
//展示要修改的培训管理信息
function show_modify_cultivate(data) {
    document.getElementById('top_div_1').style.display="none";
    var data = eval("("+data+")");
    if (data['code']==0){
        document.getElementById('top_div_2').style.display="";
        document.getElementById('f_div_id_25').style.display="";
        var datas=data['data'];
        modify_cultivate_id=datas['id'];
        var forms=document.getElementById('f_div_id_43');
        forms.howLong.value=datas['howLong'];
        forms.happenTime.value=datas['happenTime'];
        forms.score.value=datas['score'];
        forms.content.value=datas['content'];
        forms.address.value=datas['address'];
        forms.employeeNumber.value = datas['employeeNumber'];
    }else{
        alert(data['msg']);
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

function  update_cultivate_true(id) {
    login_yesOrNo();
    document.getElementById('top_div_2').style.display="none";
    document.getElementById('f_div_id_25').style.display="none";
    document.getElementById('top_div_1').style.display="";
    var form=document.getElementById(id);
    var address = form.address.value;
    var howLong = form.howLong.value;
    var happenTime = form.happenTime.value;
    var score = form.score.value;
    var content = form.content.value;
    var employeeNumber = form.employeeNumber.value;

    var param="address="+address+"@#$employeeNumber=" + employeeNumber+"@#$howLong="+howLong+"@#$happenTime="+happenTime+"@#$score="+score+"@#$content="+content+"@#$id="+modify_cultivate_id;

    ajaxRequest("POST",url+"/Cultivate/modifyCultivate",update_cultivate_handle,"text",param);
}
//修改培训管理的处理结果
function update_cultivate_handle(data) {
    var data=eval("("+data+")");
    if(data['code']==0){
        document.getElementById('top_div_1').style.display="none";
        alert("修改成功");
        login_yesOrNo();
        ajaxRequest("GET",url+"/page/returnCultivateJSP",update_cultivate_div,"text","");
    }else{
        document.getElementById('top_div_1').style.display="none";
        alert(data['msg']);
    }
}