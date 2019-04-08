//更新社会关系面板中的内容
function update_community_div(data) {
    document.getElementById('h_div_id_19').innerHTML=data;
    document.getElementById('top_div_1').style.display='none';
}

function close_community_content_panel(id) {
    document.getElementById('top_div_2').style.display='none';
    document.getElementById(id).style.display='none';
}
//社会关系上一页
function community_upPage(id,currentPageNum) {
    login_yesOrNo();
    document.getElementById('top_div_1').style.display='';
    //var currentPage = document.getElementById(id).value;
    currentPageNum--;
    ajaxRequest("GET",url+"/Community/getPage?currentPage="+currentPageNum,update_community_div,"text","");
}
//社会关系  下一页
function community_downPage(id,currentPageNum) {
    login_yesOrNo();
    document.getElementById('top_div_1').style.display='';
    //var currentPage = document.getElementById(id).value;
    ++currentPageNum;
    ajaxRequest("GET",url+"/Community/getPage?currentPage="+currentPageNum,update_community_div,"text","");
}
//社会关系 跳转到指定页面
function community_kipPage(id,pageNum,currentPageNum) {
    login_yesOrNo();
    var currentPage = document.getElementById(id).value;
    if(currentPage<=pageNum){
        ajaxRequest("GET",url+"/Community/getPage?currentPage="+currentPage,update_community_div,"text","");
    }else{
        alert("请输入在页数范围内的数字");
        document.getElementById('h_div_id_26').value=currentPageNum;
    }
}

var community_checkbox_id;
var community_select_YesOrNo=0;
//社会关系选中或取消全部复选框
function community_select_all(num) {
    var current_num=1;
    if (community_select_YesOrNo==0){
        while(current_num<=num){
            community_checkbox_id = "b_checkbox_"+current_num;
            document.getElementById(community_checkbox_id).checked = true;
            current_num++;
        }
        community_select_YesOrNo=1;
    }else{
        while(current_num<=num){
            community_checkbox_id = "b_checkbox_"+current_num;
            document.getElementById(community_checkbox_id).checked = false;
            current_num++;
        }
        community_select_YesOrNo=0;
    }
}
//删除社会关系
function del_community(num) {
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
            ajaxRequest("POST",url+"/Community/deleteCommunity",del_community_handle,"text","ids="+ids.toString());
        }else{
            document.getElementById('top_div_1').style.display='none';
        }
    }else{
        document.getElementById('top_div_1').style.display='none';
        alert("请选择要删除的数据");
    }
}
//删除社会关系的结果处理
function del_community_handle(data) {
    login_yesOrNo();
    document.getElementById('top_div_1').style.display='';
    data=eval("("+data+")");
    if (data['code']==0){
        alert("删除成功");
        ajaxRequest("GET",url+"/Community/getAllCommunitys",update_community_div,"text","");
    }else{
        alert(data['msg']);
    }
}

//根据姓名查询
function selectCollunity_byemployeename(inputId) {
    login_yesOrNo();
    var input = document.getElementById(inputId);
    var employeeName = input.employeeName.value;
    var param="employeeName="+employeeName;
    ajaxRequest("POST",url+"/Community/getEmployeeNumberByEmployeeName",select_community_handle,"text",param);
}

function select_community_handle(data) {
    login_yesOrNo();
    document.getElementById('top_div_2').style.display="none";
    var data = eval("("+data+")");
    if (data['code']==0) {
        var employeeNumber = data['data'];
        ajaxRequest("GET",url+"/Community/getCommunityByEmployeeNumber?employeeNumber="+employeeNumber,update_community_div,"text","");
    }else{
        document.getElementById('top_div_1').style.display="none";
        alert(data['msg']);
    }
}
//新增社会关系
function add_community(id) {
    document.getElementById('top_div_2').style.display='';
    document.getElementById(id).style.display='';
}

//确定社会关系
function add_community_true(formId) {

    login_yesOrNo();
    var form = document.getElementById(formId);
    var relation = form.relation.value;
    var name = form.name.value;
    var age = form.age.value;
    var political = form.political.value;
    var nation = form.nation.value;
    var work = form.work.value;
    var post = form.post.value;
    var phenomenon = form.phenomenon.value;
    var employeeNumber = form.employeeNumber.value;
    if (relation=="" || employeeNumber=="" || name=="" || age=="" || political=="" || work=="" || nation=="" || post=="" || phenomenon==""){
        alert("请将信息填写完整");
    }else{
        var param="relation="+relation+"@#$employeeNumber=" +employeeNumber+"@#$name="+name+"@#$age="+age+"@#$political="+political+"@#$nation="+nation+"@#$work="+work+"@#$post="+post+"@#$phenomenon="+phenomenon;        ajaxRequest("POST",url+"/Community/addCommunity",add_community_handle,"text",param);
    }
}
//社会关系新增结果处理
function add_community_handle(data) {
    login_yesOrNo();
    document.getElementById('top_div_2').style.display='none';
    document.getElementById('h_div_id_22').style.display='none';
    var data= eval("("+data+")");
    if(data['code']==0){
        alert(data['msg']);
        ajaxRequest("GET",url+"/page/returnCommunityJSP",update_community_div,"text","");
    }else{
        alert(data['msg']);
    }
}
//选择要修改的社会关系
function modify_community_btn(num) {
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
        ajaxRequest("GET",url+"/Community/getCommunityById?id="+id,show_modify_community,"text","");
    }else{
        document.getElementById('top_div_1').style.display="none";
        alert("请选择一条数据进行修改");
    }
}
var modify_community_id;
//展示要修改的社会关系信息
function show_modify_community(data) {
    document.getElementById('top_div_1').style.display="none";
    var data = eval("("+data+")");
    if (data['code']==0){
        document.getElementById('top_div_2').style.display="";
        document.getElementById('h_div_id_25').style.display="";
        var datas=data['data'];
        modify_community_id=datas['id'];
        var forms=document.getElementById('h_div_id_43');
        forms.relation.value=datas['relation'];
        forms.name.value=datas['name'];
        forms.age.value=datas['age'];
        forms.political.value=datas['political'];
        forms.nation.value=datas['nation'];
        forms.work.value=datas['work'];
        forms.post.value=datas['post'];
        forms.phenomenon.value=datas['phenomenon'];
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

function  update_community_true(id) {
    login_yesOrNo();
    document.getElementById('top_div_2').style.display="none";
    document.getElementById('h_div_id_25').style.display="none";
    document.getElementById('top_div_1').style.display="";
    var form=document.getElementById(id);
    var relation = form.relation.value;
    var name = form.name.value;
    var age = form.age.value;
    var political = form.political.value;
    var nation = form.nation.value;
    var work = form.work.value;
    var post = form.post.value;
    var phenomenon = form.phenomenon.value;
    var employeeNumber = form.employeeNumber.value;

    var param="relation="+relation+"@#$employeeNumber=" + employeeNumber+"@#$name="+name+"@#$age="+age+"@#$political="+political+"@#$nation="+nation+"@#$work="+work+"@#$post="+post+"@#$phenomenon="+phenomenon+"@#$id="+modify_community_id;

    ajaxRequest("POST",url+"/Community/modifyCommunity",update_community_handle,"text",param);
}
//修改社会关系的处理结果
function update_community_handle(data) {
    var data=eval("("+data+")");
    if(data['code']==0){
        document.getElementById('top_div_1').style.display="none";
        alert("修改成功");
        login_yesOrNo();
        ajaxRequest("GET",url+"/page/returnCommunityJSP",update_community_div,"text","");
    }else{
        document.getElementById('top_div_1').style.display="none";
        alert(data['msg']);
    }
}