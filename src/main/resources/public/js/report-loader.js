function showimage()
{
if (!document.images)
return
document.images.pictures.src=
document.mygallery.picture.options[document.mygallery.picture.selectedIndex].value
}