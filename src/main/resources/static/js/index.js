/*<![CDATA[*/
	var message = [[${msg}]];
	if(message != null) alert(message);
	var deleteForm = document.getElementById('deleteForm');
	var deleteBtn = document.getElementById('deleteBtn');
	deleteBtn.addEventListener('click', function(){
	var ret = confirm("削除します。よろしいですか？");
	if(ret) {
		deleteForm.submit();
	}
	}, false)
/*]]>*/