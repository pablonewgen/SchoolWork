<!--

IMPORTANT, IMPORTANT:  You must set permissions to 775 for the destination folder
for uploaded images.  However, you must not--under any circumstances--set 775 or 777 
permissions on any other folders, nor can your upload directory be visible, ever.

NOTE:  This example works correctly but is incomplete.

$_FILES array elements:

    $_FILES["file"]["name"] - the name of the uploaded file
    $_FILES["file"]["type"] - the type of the uploaded file
    $_FILES["file"]["size"] - the size in bytes of the uploaded file
    $_FILES["file"]["tmp_name"] - the name of the temporary copy of the file stored on the server
    $_FILES["file"]["error"] - the error code resulting from the file upload
    
    
 UPLOAD_ERR_OK         Value: 0; There is no error, the file uploaded with success.
 UPLOAD_ERR_INI_SIZE   Value: 1; The uploaded file exceeds the upload_max_filesize directive in php.ini.
                                 This value is set on jadran to 2MB.
 UPLOAD_ERR_FORM_SIZE  Value: 2; The uploaded file exceeds the MAX_FILE_SIZE directive that was specified in the HTML form.
 UPLOAD_ERR_PARTIAL    Value: 3; The uploaded file was only partially uploaded.
 UPLOAD_ERR_NO_FILE    Value: 4; No file was uploaded.
 UPLOAD_ERR_NO_TMP_DIR Value: 6; Missing a temporary folder. Introduced in PHP 4.3.10 and PHP 5.0.3.
 UPLOAD_ERR_CANT_WRITE Value: 7; Failed to write file to disk. Introduced in PHP 5.1.0.
 UPLOAD_ERR_EXTENSION  Value: 8; File upload stopped by extension. Introduced in PHP 5.2.0.
 
 exif_imagetype constants: IMAGETYPE_JPEG, IMAGETYPE_GIF, IMAGETYPE_PNG, IMAGETYPE_BMP    
-->

<?php
	$msg = "";
    $UPLOAD_DIR = '_apics_/';
    $COMPUTER_DIR = '/home/jadrn034/public_html/proj3/_apics_/';
    $fname = $_FILES['file']['name'];
    

    if(file_exists("$UPLOAD_DIR".$fname))  {
        $msg.= "<b>Error, the file $fname already exists on the server</b><br />\n";
        }
    elseif($_FILES['file']['error'] > 0) {
    	$err = $_FILES['file']['error'];	
        $msg .= "Error Code: $err ";
	if($err == 1)
		$msg.= "The file was too big to upload, the limit is 2MB<br />";
        } 
    elseif(exif_imagetype($_FILES['file']['tmp_name']) != IMAGETYPE_JPEG) {
        $msg .= "ERROR, not a jpg file<br />";   
        }
## file is valid, copy from /tmp to your directory.        
    else { 
        move_uploaded_file($_FILES['file']['tmp_name'], "$UPLOAD_DIR".$fname);
	}
	
	if($msg) {
        write_form_error_page($msg);
        exit;
        }
        
?>  
        

</body>
</html>     
    