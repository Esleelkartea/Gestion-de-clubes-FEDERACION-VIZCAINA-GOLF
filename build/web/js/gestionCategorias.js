

function crearArbol(){
	tree=new dhtmlXTreeObject("Barbol","100%","100%",0);
	tree.setImagePath("imagenes/arbol/");
	tree.enableDragAndDrop(0);
	tree.enableTreeLines(false);
	tree.setOnClickHandler(tonclick);
	tree.setImageArrays("plus","","","","plus.gif");
	tree.setImageArrays("minus","","","","minus.gif");
	tree.setStdImages("iconWrite1.gif","iconWrite1.gif","iconWrite1.gif");
	tree.loadXML("tree.xml");
}

function tonclick(id){
		categoria=tree.getItemText(id);
		document.GestionCategorias.nombreCategoria.value=categoria;
		document.GestionCategorias.ultimoSeleccionado.value=id;
};