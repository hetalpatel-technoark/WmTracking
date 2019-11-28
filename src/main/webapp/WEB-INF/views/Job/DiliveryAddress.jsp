<%@page import="com.wmtrucking.entities.MaJobs"%>
<%@page import="com.wmtrucking.utils.CheckInput"%>

<%
        MaJobs majob= null;
        if(request.getAttribute("majob")!=null){
         majob= (MaJobs) request.getAttribute("majob");
        }
  CheckInput checkInput = new CheckInput();
%>
<div class="form-group row">
    <div class="col-md-4">
        <span>Address 1</span>
    </div>
    <div class="col-md-8">
        <input type="text" id="first-name" class="form-control" name="add1" value="<%=checkInput.checkValueEdit(majob.getAddress1(),request.getParameter("add1"))%>" placeholder="Address 1">
    </div>
</div>
<div class="form-group row">
    <div class="col-md-4">
        <span>Address 2</span>
    </div>
    <div class="col-md-8">
        <input type="text" id="first-name" class="form-control" name="add2" value="<%=checkInput.checkValueEdit (majob.getAddress2(),request.getParameter("add2"))%>" placeholder="Address 2">
    </div>
</div>
<div class="form-group row">
    <div class="col-md-4">
        <span>Address 3</span>
    </div>
    <div class="col-md-8">
        <input type="text" id="first-name" class="form-control" name="add3" value="<%=checkInput.checkValueEdit (majob.getAddress3(),request.getParameter("add3"))%>" placeholder="Address 3">
    </div>
</div>


<div class="form-group row">
    <div class="col-md-4">
        <span>City</span>
    </div>
    <div class="col-md-8">
        <input type="text"  id="first-name" class="form-control" name="city" value="<%=checkInput.checkValueEdit (majob.getCity(),request.getParameter("city"))%>" placeholder="City">
    </div>
</div>
<div class="form-group row">
    <div class="col-md-4">
        <span>State</span>
    </div>
    <div class="col-md-8">
        <input type="text" id="state" class="form-control" name="state" value="<%=checkInput.checkValueEdit (majob.getState(),request.getParameter("state"))%>" placeholder="State">
    </div>
</div>
<div class="form-group row">
    <div class="col-md-4">
        <span>Country</span>
    </div>
    <div class="col-md-8">
        <input type="text" id="country" class="form-control" name="country" value="<%=checkInput.checkValueEdit (majob.getCountry(),request.getParameter("country"))%>" placeholder="Country">
    </div>
</div>
<div class="form-group row">
    <div class="col-md-4">
        <span>Pincode</span>
    </div>
    <div class="col-md-8">
        <input type="text"  id="pincode" class="form-control" name="pin" value="<%=checkInput.checkValueEdit (majob.getPincode(),request.getParameter("pin"))%>" placeholder="Pincode">
    </div>
</div>