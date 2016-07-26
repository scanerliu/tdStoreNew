function searchProductAttribute(t){
	var url = basePath + "/admin/productattribute/search";
	var loadData = null;
	
	if(t){
		loadData = null;
	}else{
		loadData = $("#productAttributeform").serializeArray();
	}
	$("#results").load(url,loadData);
}

function editProductAttribute(id){
	var url = basePath + "/admin/productattribute/edit";
	var loadData = {"id" : id};
	$("#rightform").load(url,loadData);
	showForm();
}

function showForm(){
	$("#rightlist").hide();
	$("#rightform").show();
}
function returnList(){
	$("#rightlist").show();
	$("#rightform").html("").hide();
}

function refreshList(){
	searchProductAttribute(false);
}

// 提交新增、修改广告为内容
function saveAttribute(){
	var f = $('#attributeForm').form('enableValidation').form('validate');
	if(f){
		$("#attributeForm").form("submit",{
			success : function(data){
				 var result = eval("("+data+")");
				  if(result.code==1){
					  $.messager.alert('消息提醒','规格类型保存成功。');
					  returnList();
					  refreshList();
				  }else{
					  $.messager.alert('消息提醒','规格类型保存失败!');
				  }
			}
		});
		
	}
}

function delProductAttribute(id){
	$.messager.confirm('消息提醒', '删除后该类型后，其内容将同步删除，确定要继续删除吗?', function(r){
		if (r){
			var url = basePath+"/admin/productattribute/delete";
			var loadData={"id":id};
			$.post(url,loadData,delProductTypeCallback,"text");
		}
	});
}

function delProductTypeCallback(data){
	var result = eval("("+data+")");
	if(result.code==1){
		$.messager.alert('消息提醒','删除成功。');
		refreshList();
	}else{
		$.messager.alert('消息提醒','删除失败!');
	}
}

function searchOption(id)
{
	var url = basePath+"/admin/attribute/option/search";
	var loadData = {"attriId":id};
	$("#results").load(url,loadData);
}

/*
function myLoadFilter(data,parentId){
	concole.debug("json in")
function setData(){
	 var todo = [];
	 for(var i=0; i<data.length; i++){
		 todo.push(data[i]);
	 }
	 while(todo.length){
		 var node = todo.shift();
		 if (node.children){
		  node.state = 'closed';
		  node.children1 = node.children;
		  node.children = undefined;
		  todo = todo.concat(node.children1);
		 }
	 }
}

setData(data);

	var tg = $(this);
	var opts = tg.treegrid('options');
	opts.onBeforeExpand = function(row){
	 if (row.children1){
		 tg.treegrid('append',{
			  parent: row[opts.idField],
			  data: row.children1
		 });
		 row.children1 = undefined;
		 tg.treegrid('expand', row[opts.idField]);
	 }
	 return row.children1 == undefined;
	};
	return data;
}

*/