<script type="text/ng-template" id="loginModal.jsp">
    <div class="modal-header">
        <h3 class="modal-title">Iniciar Sesion!</h3>
    </div>
    <div class="modal-body">
        <%@include file="loginForm.jsp" %>
    </div>
    <div class="modal-footer">
        <!--<button class="btn btn-primary" type="button" ng-click="ok()">OK</button>-->
        <button class="btn btn-warning" type="button" ng-click="cancel()">Cancel</button>
    </div>
</script>