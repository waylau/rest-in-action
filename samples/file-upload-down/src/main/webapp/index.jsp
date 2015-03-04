<html>
<head>
    <meta charset="UTF-8">
</head>
<body>
    <h2>Jersey RESTful Web Application!</h2>
    <p><a href="webapi/myresource">Jersey resource</a>
    <p><a href="webapi/myresource/pojojson">POJO JSON</a>
    <p><a href="webapi/myresource/pojoxml">POJO XML</a>
    <p><a href="webapi/files/download">Download</a>

	<h3>Upload a File</h3>
	<form action="webapi/files/upload" method="post" enctype="multipart/form-data">
	   <p>
		Select a file : <input type="file" name="file" size="50" />
	   </p>
	   <input type="submit" value="Upload It" />
	</form>

 
 
    <p>Visit <a href="www.waylau.com">www.waylau.com</a>
    for more information on Jersey!
</body>
</html>
