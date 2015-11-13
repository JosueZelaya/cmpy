<form:form method="post" action="/publicacion/agregarPublicacion" enctype="multipart/form-data">
    <label for="titulo">Agregue el nombre del producto</label>
    <input path="titulo" id="titulo" type="text" name="titulo" class="form-control" placeholder="Titulo del anuncio"/>
    <br/>
    <label for="precio">Precio del Producto</label>
    <input name="precio" type="number" min="0" max="2000" id="precio" class="form-control">
    <br/>
    <label for="titulo">Describa el producto a vender</label>
    <textarea path="descripcion" id="descripcion" name="descripcion" class="form-control" rows="3" placeholder="Agrege una descripcion de su producto"></textarea>                                            
    <br/>
    <label for="imagenes">Suba imagenes de su producto</label>
    <div class="form-group">
        <input path="multipleFiles" id="imagenes" name="multipleFiles" type="file" class="file" multiple=true data-preview-file-type="any"/>
    </div>                    

    <div class="modal-footer">                
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button value="VenderYa!" type="submit" class="btn btn-success pull-right">
            VenderYa!
        </button>
    </div>
  <!--<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>-->
</form:form> 