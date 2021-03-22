var path = "";
$(function () {
    showSelect();
    showTable("","","");
    showTime();
    showFormSelects();
    //文本框模糊搜索
    /* $("#keyword").keyup(function() {
        var keyword =document.getElementById('keyword').value;
        showTable('','','');
    }) */
    $('#addImg1').click(function(){
        $('#file').click();
    });
    $('#addImg2').click(function(){
        $('#file2').click();
    });
    $.ajax({
        type:'GET',
        url:path + "/defect/getLoginUserInfo",
        success:function(data){
            $("#addCreaterName").text(data.userName);
            $("#feedbackCompleterName").text(data.userName);
            $('#addUserId').val(data.id);
            $('#feedbackCompleterId').val(data.id);
        }
    })
});
//显示下拉框信息
function showSelect() {
    layui.use(['form'],function(){
        var form = layui.form;
        $.ajax({
            type:'GET',
            url:path + "/defect/getEquMap",
            data:{type: 1},
            success:function(data){
                $("#system").empty();
                $("#addSystem").empty();
                var option = "<option value='-1' >请选择系统</option>";
                for (var i = 0; i < data.length; i++) {
                    option += "<option value='" + data[i].id + "'>" + data[i].text + "</option>"
                }
                $('#system').html(option);
                $('#addSystem').html(option);
                form.render();//菜单渲染 把内容加载进去
                form.render('select');
            }
        });
        form.on('select(system)', function(data) {
            $("#systemHidden").val(data.value);
            showTable('',$("#systemHidden").val(),$("#systemHidden").val());
        });
        form.on('select(addSystem)', function(data) {
            $("#addSystemHidden").val(data.value);
        });
        $.ajax({
            type:'GET',
            url:path + "/defect/getEquMap",
            data:{type: 2},
            success:function(data){
                $("#equipment").empty();
                $("#addEquipment").empty();
                var option = "<option value='-1' >请选择设备</option>";
                for (var i = 0; i < data.length; i++) {
                    option += "<option value='" + data[i].id + "'>" + data[i].text + "</option>"
                }
                $('#equipment').html(option);
                $('#addEquipment').html(option);
                form.render();//菜单渲染 把内容加载进去
                form.render('select');
            }
        });
        form.on('select(equipment)', function(data) {
            $("#equipmentHidden").val(data.value);
            showTable('',$("#systemHidden").val(),$("#equipmentHidden").val());
        });
        form.on('select(addEquipment)', function(data) {
            $("#addEquipmentHidden").val(data.value);
        });
        form.on('select(level)', function(data) {
            $("#levelHidden").val(data.value);
        })
        form.on('select(maintenanceCategory)', function(data) {
            $("#maintenanceCategoryHidden").val(data.value);
        });
    });
}
//显示日期
function showTime() {
    layui.use('laydate', function() {
        var laydate = layui.laydate;
        //点我触发
        laydate.render({
            elem: '#test1'
            , trigger: 'click'
            ,done: function(value){
            }
        });
        laydate.render({
            elem: '#test2'
            , type: 'time'
            ,format: 'HH'
            , trigger: 'click'
            ,done: function(value){
            }
        });
    });
}
//点击按钮
function getChecked (type) {
    if (type == '0') { //全部缺陷
        showTable('0',$("#systemHidden").val(),$("#equipmentHidden").val());
    } else if (type == '1') { //未认领
        showTable('1',$("#systemHidden").val(),$("#equipmentHidden").val());
    } else if (type == '2') { //消缺中
        showTable('2',$("#systemHidden").val(),$("#equipmentHidden").val());
    } else if (type == '3') { //已消缺
        showTable('3',$("#systemHidden").val(),$("#equipmentHidden").val());
    } else if (type == '4') { //已完成
        showTable('3',$("#systemHidden").val(),$("#equipmentHidden").val());
    }
}
//显示表格
function showTable(type,sysId,equipmentId) {
    var win = $(window).height();
    var height = win-160;
    layui.use('table', function() {
        var table = layui.table;
        table.render({
            elem: '#demo'
            , height: height
            , url: path + '/defect/getDefectList?type='+type+'&sysId='+sysId+'&equipmentId='+equipmentId//数据接口
            , page: true //开启分页
            , limit: 50
            , limits: [50,100,150]
            , id: 'demoInfo'
            , cols: [[ //表头
                {field: 'number', title: '缺陷编号', event: 'detailed', style:'cursor: pointer;color:blue;', align: 'center', sort: true}
                , {field: 'sysName', title: '所属系统', align: 'center'}
                , {field: 'equipmentName', title: '设备名称', align: 'center'}
                , {field: 'abs', title: '缺陷描述', align: 'center'}
                , {field: 'created', title: '申请时间', align: 'center'}
                , {field: '', title: '重大级别', toolbar: "#tbMajorBar", align: 'center'}
                , {field: 'createdByName', title: '创建人', align: 'center'}
                , {field: 'departmentName', title: '处理部门', align: 'center'}
                , {fixed: '', title: '状态', toolbar: '#tbTypeBar', align: 'center'}
                , {field: 'planedTime', title: '计划完成时间', align: 'center'}
                , {field: 'realETime', title: '实际完成时间', align: 'center'}
                , {fixed: '', title: '认领人', toolbar: '#tbClaimBar', align: 'center'}
                , {fixed: '', title: '消缺反馈', toolbar: '#tbHandleBar', align: 'center'}
                , {fixed: '', title: '值班确认', toolbar: '#tbBeOnDutyBar', align: 'center'}
            ]]
            , parseData: function (res) {
            }
            , done: function (res, curr, count) {
            }
        });
        table.on('tool(test)', function(obj){ //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
            var data = obj.data; //获得当前行数据
            var jStr =JSON.stringify(data);
            if (obj.event === 'beOnDuty') {// 值班确认
                if (data.type != "3") {
                    layer.alert("请先完成任务！");
                    return;
                }
                layer.open({
                    type: 1
                    ,title: false //不显示标题栏
                    ,closeBtn: false
                    ,area: '300px;'
                    ,shade: 0.8
                    ,id: 'LAY_layuipro' //设定一个id，防止重复弹出
                    ,btn: ['确定', '取消']
                    ,btnAlign: 'c'
                    ,moveType: 1 //拖拽模式，0或者1
                    ,content: '<div style="padding: 50px 10px 50px 17px; box-sizing: border-box; line-height: 22px; background-color: #95b3ca; color: #000; font-weight: 500;font-size: 18px;">确认已完成本次消缺吗？</div>'
                    ,success: function(layero){
                        var btn = layero.find('.layui-layer-btn');
                        btn.find('.layui-layer-btn0').click(function () {
                            $.ajax({
                                "type" : 'put',
                                "url": path + "/defect/dutyConfirmation",
                                data: {id:data.id},
                                dataType: "json",
                                "success":function(data){
                                    ajaxFun(data,"值班确认成功!");
                                }
                            });
                        });
                    }
                });
            } else if (obj.event === 'claim'){ //认领
                $("#claimInfoP").text(jStr);
                claimInfo();
            } else if (obj.event === 'handle'){ //消缺反馈
                if (data.type == 1) {
                    layer.alert("请先认领");
                    return;
                }
                $("#handleInfoP").text(jStr);
                handleInfo();
            } else if (obj.event === 'detailed'){ //缺陷详情
                $("#detailedInfoP").text(jStr);
                detailedInfo();
            }
        });
    });
}
//显示新增页面
function addDefect () {
    layui.use('layer', function() { //独立版的layer无需执行这一句
        var $ = layui.jquery, layer = layui.layer, form = layui.form; //独立版的layer无需执行这一句
        layer.open({
            type: 1
            , id: 'addDefectDiv' //防止重复弹出
            , content: $(".addDefectDiv")
            , btnAlign: 'c' //按钮居中
            , shade: 0.4 //不显示遮罩
            , area: ['100%', '100%']
            , yes: function () {
            }
        });
        $('#levelHidden').val(0);
        $('#level').val(0);
        $('#maintenanceCategoryHidden').val(1);
        $('#maintenanceCategory').val(1);
        $('#addAbs').val("");
        $('#addSystemHidden').val('-1');
        $('#addSystem').val('-1');
        $('#addEquipment').val('-1');
        $('#addEquipmentHidden').val('-1');
        form.render();//菜单渲染 把内容加载进去
        form.render('select');
    });
    var myDate = new Date();
    var year =  myDate.getFullYear();
    var month = myDate.getMonth()+1;
    var date = myDate.getDate();
    if (month < 10) {
        month = "0"+month;
    }
    if (date < 10) {
        date = "0"+date;
    }
    $("#createTime").html(year+"-"+month+"-"+date);
    $("#img-change1").attr("src","");
}
//确定添加
function insert () {
    $.ajaxFileUpload({
        url: path + '/defect/imgUpload',
        fileElementId: 'file',
        dataType: 'json',
        secureuri : false,
        success: function (Json){
            var defect = {};
            defect.level = Number($('#levelHidden').val());//重大级别
            defect.maintenanceCategory = Number($('#maintenanceCategoryHidden').val());//检修类别
            defect.equipmentId = Number($('#addEquipmentHidden').val());//设备id
            defect.sysId = Number($('#addSystemHidden').val());//设备id
            defect.abs = $('#addAbs').val();//缺陷描述
            defect.bPlc = Json.message;//图片
            defect.createBy = Number($('#addUserId').val());//申请人员ID
            if(defect.sysId == -1){
                layer.alert("请选择系统!");
                return;
            }
            if(defect.equipmentId == -1){
                layer.alert("请选择设备!");
                return;
            }
            if(!defect.abs){
                layer.alert("缺陷描述不可以为空!");
                return;
            }
            if (defect.bPlc == "") {
                layer.alert("请上传图片！");
                return;
            }
            $.ajax({
                "type" : 'post',
                "url": path + "/defect/addDefect",
                data: JSON.stringify(defect),
                dataType: "json",
                contentType: "application/json; charset=utf-8",
                "success":function(data){
                    ajaxFun(data,"缺陷添加成功!");
                }
            });
        }
    });
}
//上传图片
function fileChange(event,id,num){
    var files = event.target.files, file;
    if (files && files.length > 0) {
        // 获取目前上传的文件
        file = files[0];// 文件大小校验的动作
        if(file.size > 1024 * 1024 * 2) {
            files = null;
            $("#"+id).val("");
            layer.alert("图片大小不能超过 2MB!!");
            return false;
        }
        // 获取 window 的 URL 工具
        var URL = window.URL || window.webkitURL;
        // 通过 file 生成目标 url
        var imgURL = URL.createObjectURL(file);
        //用attr将img的src属性改成获得的url
        if (num == "one") {
            $("#img-change1").attr("src",imgURL);
            $("#img-change1").css("display","block");
        } else if (num == "two"){
            $("#img-change2").attr("src",imgURL);
            $("#img-change2").css("display","block");
        }
        // 使用下面这句可以在内存中释放对此 url 的伺服，跑了之后那个 URL 就无效了
        // URL.revokeObjectURL(imgURL);
    }
};
//删除图片
function reduceImg(id) {
    var id = $("#"+id);
    // document.getElementById("file").parentNode.removeChild(document.getElementById("file"));
    var img = id.prev();
    img.attr("src","");
    img.css("display","none");
    // id.css("display","none");
}
//缺陷详情
function detailedInfo() {
    var data = $("#detailedInfoP").text().trim();
    // 将字符串转换为json
    data = eval('(' + data + ')');
    $("#detailedInfoId").text(data.number);//编号
    $("#detailedInfoSys").text(data.sysName);//系统
    $("#detailedInfoLevel").text(data.level+"类");//级别
    if (data.maintenanceCategory == 1) {//类别
        $("#detailedInfoMan").text("机务");
    } else {
        $("#detailedInfoMan").text("电仪");
    }
    if (data.maintenanceCategory == 1) {//状态
        $("#detailedInfoStatus").text("未认领");
    } else if (data.maintenanceCategory == 2) {
        $("#detailedInfoStatus").text("消缺中");
    }if (data.maintenanceCategory == 3) {
        $("#detailedInfoStatus").text("已完成");
    }
    $("#detailedInfoCreateName").text(data.createdByName);//创建人
    $("#detailedInfoCreateTime").text(data.created);//创建时间
    $("#detailedInfoStaff").text(data.empIdsName);//消缺人
    $("#detailedInfoPlanedTime").text(data.planedTime);//计划完成时间
    $("#detailedInfoRealETime").text(data.realETime);//实际完成时间
    $("#detailedInfoAbs").val(data.abs);//缺陷描述
    $("#detailedInfoMethod").val(data.method);//处理措施
    $("#detailedInfoProblem").val(data.problem);//遗留问题
    $("#detailedInfoRemark").val(data.remark);//备注
    $("#detailedInfoBImg").attr("src", "data:img/jpeg;base64,"+data.bPlc64);//消缺前图片
    $("#detailedInfoAImg").attr("src", "data:img/jpeg;base64,"+data.aPlc64);//消缺后图片
    layui.use('layer', function() { //独立版的layer无需执行这一句
        var $ = layui.jquery, layer = layui.layer; //独立版的layer无需执行这一句
        layer.open({
            type: 1
            , id: 'detailedInfoDiv' //防止重复弹出
            , content: $(".detailedInfoDiv")
            , btnAlign: 'c' //按钮居中
            , shade: 0.4 //不显示遮罩
            , area: ['100%', '100%']
            , yes: function () {
            }
        });
    });
}
//打开认领缺陷
function claimInfo () {
    var data = $("#claimInfoP").text().trim();
    data = eval('(' + data + ')');
    $("#claimId").val(data.id);//编号
    $("#claimInfoId").text(data.number);//编号
    $("#claimInfoSys").text(data.sysName);//系统
    $("#claimInfoLevel").text(data.level+"类");//级别
    $("#claimInfoCreateName").text(data.createdByName);//创建人
    $("#claimInfoCreateTime").text(data.created);//创建时间
    $("#claimInfoAbs").val(data.abs);//缺陷描述
    $("#claimInfoImg").attr("src", "data:img/jpeg;base64,"+data.bPlc64);//消缺前图片
    layui.use('layer', function() { //独立版的layer无需执行这一句
        var $ = layui.jquery, layer = layui.layer; //独立版的layer无需执行这一句
        layer.open({
            type: 1
            , id: 'claimInfoDiv' //防止重复弹出
            , content: $(".claimInfoDiv")
            , btnAlign: 'c' //按钮居中
            , shade: 0.4 //不显示遮罩
            , area: ['100%', '100%']
            , yes: function () {
            }
        });
    });
}
//认领下拉多选框
function showFormSelects () {
    layui.use(['jquery', 'formSelects'], function(){
        var formSelects = layui.formSelects;
        formSelects.config('tags', {
            keyName: 'text',
            keyVal: 'id',
        }).data('tags', 'server', {
            url: path + '/defect/getEmpMap'
        });
        formSelects.closed('tags', function(id){
            $("#claimInfoDescribeId").val(layui.formSelects.value('tags', 'val'));
        });
    });
}
//认领缺陷
function claimOk() {
    var empIds = $("#claimInfoDescribeId").val();
    var id = $("#claimId").val();
    var planedTime = $("#test1").val()+" "+$("#test2").val();
    if (empIds == "" || empIds == null) {
        layer.alert("请选择认领人员！");
        return;
    }
    $.ajax({
        "type" : 'post',
        "url": path + "/defect/claim",
        dataType: "json",
        data: {empIds:empIds,id:id,planedTime:planedTime},
        "success":function(data){
            ajaxFun(data,"缺陷认领成功!");
        }
    });
}
//打开处理反馈
function handleInfo () {
    var data = $("#handleInfoP").text().trim();
    data = eval('(' + data + ')');
    $("#feedbackId").val(data.id);//id
    $("#feedbackType").val(data.type);//type
    $("#feedbackLevel").text(data.level+"类");//级别
    if (data.maintenanceCategory == 1) {//类别
        $("#feedbackDepartment").text("机务");
    } else {
        $("#feedbackDepartment").text("电仪");
    }
    $("#feedbackTime").text(data.year);//创建时间
    $("#feedbackRealSTime").text(data.realSTime);//开始执行时间
    $("#feedbackSys").text(data.sysName);//系统
    $("#feedbackEquipment").text(data.equipmentName);//设备
    $("#feedbackAbs").val(data.abs);//缺陷描述
    $("#feedbackSituation").val(data.situation);//缺陷情况
    $("#feedbackMethod").val(data.method);//处理措施
    $("#feedbackProblem").val(data.problem);//遗留问题
    $("#feedbackRemark").val(data.remark);//备注
    $("#img-change2").attr("src", "data:img/jpeg;base64,"+data.aPlc64);//消缺后图片
    $("#img-change2").css("display","none");
    if (data.realSTime == null || data.realSTime == "") {
        $("#startFeedbackBtn").css("display","revert");
        $("#feedbackAbs").attr({"disabled":"disabled"});
        $("#feedbackSituation").attr({"disabled":"disabled"});
        $("#feedbackMethod").attr({"disabled":"disabled"});
        $("#feedbackProblem").attr({"disabled":"disabled"});
        $("#feedbackRemark").attr({"disabled":"disabled"});
    } else {
        $("#startFeedbackBtn").css("display","none");
        $("#feedbackAbs").removeAttr("disabled");
        $("#feedbackSituation").removeAttr("disabled");
        $("#feedbackMethod").removeAttr("disabled");
        $("#feedbackProblem").removeAttr("disabled");
        $("#feedbackRemark").removeAttr("disabled");
    }
    if (data.realETime == null || data.realETime == "") {
        $("#insertFeedbackBtn").css("display","revert");
    } else {
        $("#insertFeedbackBtn").css("display","none");
    }
    layui.use('layer', function() { //独立版的layer无需执行这一句
        var $ = layui.jquery, layer = layui.layer; //独立版的layer无需执行这一句
        layer.open({
            type: 1
            , id: 'handleInfoDiv' //防止重复弹出
            , content: $(".handleInfoDiv")
            , btnAlign: 'c' //按钮居中
            , shade: 0.4 //不显示遮罩
            , area: ['100%', '100%']
            , yes: function () {
            }
        });
    });
}
//开始执行
function startFeedback() {
    var id = Number($("#feedbackId").val());
    $.ajax({
        "type" : 'put',
        "url": path + "/defect/startExecution",
        data: {id: id},
        dataType: "json",
        "success":function(data){
            $("#startFeedbackBtn").css("display","none");
            $("#feedbackAbs").removeAttr("disabled");
            $("#feedbackSituation").removeAttr("disabled");
            $("#feedbackMethod").removeAttr("disabled");
            $("#feedbackProblem").removeAttr("disabled");
            $("#feedbackRemark").removeAttr("disabled");
            ajaxFun(data,"执行开始!");
        }
    });
}
//处理反馈
function insertFeedback () {
    $.ajaxFileUpload({
        url: path + '/defect/imgUpload',
        fileElementId: 'file2',
        dataType: 'json',
        secureuri : false,
        success: function (Json){
            if ($("#feedbackRealSTime").text() == null || $("#feedbackRealSTime").text() == "") {
                layer.alert("请点击开始执行");
                return;
            }
            var date = new Date();
            var year = date.getFullYear();
            var month = date.getMonth()+1;
            var day = date.getDate();
            var hour = date.getHours();
            var defect = {};
            defect.type = Number($('#feedbackType').val());
            if(defect.type>=3){
                layer.alert("缺陷已完成不可更改反馈单!");
                return;
            }
            defect.id = Number($('#feedbackId').val());
            defect.feedbackAbs = $('#feedbackAbs').val();
            if(!defect.feedbackAbs){
                layer.alert("缺陷反馈不可为空!");
                return;
            }
            defect.situation = $('#feedbackSituation').val();
            if(!defect.situation){
                layer.alert("缺陷情况不可为空!");
                return;
            }
            defect.method = $('#feedbackMethod').val();
            if(!defect.method){
                layer.alert("处理措施不可为空!");
                return;
            }
            defect.problem = $('#feedbackProblem').val();
            defect.remark = $('#feedbackRemark').val();
            defect.completer = Number($('#feedbackCompleterId').val());
            defect.aPlc = Json.message;//图片
            defect.realETime = year +"-"+month+"-"+day+" "+hour;
            if (defect.aPlc == "") {
                layer.alert("请上传图片！");
                return;
            }
            $.ajax({
                "type" : 'put',
                "url": path + "/defect/updDefect",
                data: JSON.stringify(defect),
                dataType: "json",
                contentType: "application/json; charset=utf-8",
                "success":function(data){
                    ajaxFun(data,"处理反馈单完成!");
                }
            });
        }
    });
}
//执行函数
function ajaxFun(data,tips) {
    if (data == "SUCCESS"){
        layer.alert(tips);
        showTable('',$("#systemHidden").val(),$("#equipmentHidden").val());
        layer.closeAll();
    } else if (data == "NOUSER"){
        layer.alert("用户验证信息过期!");
    } else if (data == "REJECT"){
        layer.alert("记录已经被修改!");
    } else if (data == "FORMAT") {
        layer.alert("格式错误!");
    }  else if (data == "HAVE") {
        layer.alert("存在同名!");
    } else {
        layer.alert("后台错误!");
    }
}
//取消
function cancel() {
    layer.closeAll();
}