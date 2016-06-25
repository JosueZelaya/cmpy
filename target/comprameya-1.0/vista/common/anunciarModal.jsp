<script type="text/ng-template" id="anunciarModal.jsp">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h3 class="modal-title">Anunciate y Vende más!</h3>
    </div>
    <div class="modal-body">
        <%@include file="anuncioForm.jsp" %>        
    </div>
    <div class="modal-footer">
        <!--<button class="btn btn-primary" type="button" ng-click="ok()">OK</button>-->
        <button class="btn btn-warning" type="button" ng-click="cancel()">Cancel</button>
    </div>
</script>