<%@page import="java.util.Iterator"%>
<%@page import="com.wmtrucking.utils.CheckInput"%>
<%@page import="com.wmtrucking.entities.MaCustomer"%>
<%@page import="com.wmtrucking.entities.MaDriver"%>

<%@page import="java.util.List"%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/assets-new/app-assets/vendors/css/pickers/pickadate/pickadate.css">  

<jsp:include page="../Template/header.jsp"></jsp:include>

    <div class="content-wrapper">
        <div class="content-header row">
            <div class="content-header-left col-md-9 col-12 mb-2">
                <div class="row breadcrumbs-top">
                    <div class="col-12">
                        <h2 class="content-header-title float-left mb-0">Job</h2>
                        <div class="breadcrumb-wrapper col-12">
                            <ol class="breadcrumb">
                                <li class="breadcrumb-item"><a href="<%= request.getContextPath()%>/Dashboard/Dashboard">Home</a>
                            </li>
                            <li class="breadcrumb-item"><a href="<%= request.getContextPath()%>/job/List">Job</a>
                            </li>
                            <li class="breadcrumb-item"><a href="#">Create</a>
                            </li>
                        </ol>
                    </div>
                </div>
            </div>
        </div>        
    </div>
    <div class="content-body">
        <!-- Basic Horizontal form layout section start -->
        <section id="basic-horizontal-layouts">
            <div class="row match-height">
                <div class="col-md-12 col-12">
                    <%
                        if (request.getAttribute("error") != null) {
                            List<String> errors = (List<String>) request.getAttribute("error");
                            if (!errors.isEmpty()) {
                    %>
                    <div class="alert alert-danger">
                        <button class="close" data-dismiss="alert"><span>x</span></button>
                        <%
                                Iterator<String> iterator = errors.iterator();
                                while (iterator.hasNext()) {
                                    out.println("<i class='fa fa-arrow-circle-right'></i> " + iterator.next() + "<br/>");
                                }
                            }
                        %>
                    </div>
                    <%                        }
                        CheckInput checkInput = new CheckInput();
                    %>

                    <div class="card">
                        <div class="card-content">
                            <div class="card-body">
                                <form method="post"  class="form-horizontal bordered-row" id="user-form" action="<%=request.getContextPath()%>/job/PostCreate">
                                    <div class="form-body">
                                        <div class="row">
                                            <div class="col-6">
                                                <div class="form-group row">
                                                    <div class="col-md-4">
                                                        <span>Assign Driver *</span>
                                                    </div>
                                                    <div class="col-md-8">
                                                        <select class="form-control select-class" name="driver" required>
                                                            <option selected="" disabled="">Select Driver </option>
                                                            <%
 List<MaDriver> maDrivers = (List<MaDriver>) request.getAttribute("maDriver");
 for (MaDriver maDriver : maDrivers) {
                                                            %>
                                                            <option  value="<%=maDriver.getId() %>"><%= maDriver.getFirstname()%></option>
                                                            <%}%>
                                                        </select>
                                                    </div>
                                                </div> 

                                                <div class="form-group row">
                                                    <div class="col-md-4">
                                                        <span>Customers/Company*</span>
                                                    </div>
                                                    <div class="col-md-8">
                                                        <select onchange="searchAddress()" id="customer" class="form-control select-class" name="customer" required>
                                                            <option selected="" disabled="">Select Customer </option>

                                                            <%
                                                            List<MaCustomer> maCustomers = (List<MaCustomer>) request.getAttribute("maCustomer");
 for (MaCustomer maCustomer : maCustomers) {
                                                            %>
                                                            <option  value="<%=maCustomer.getId() %>"><%= maCustomer.getFirstname()%></option>
                                                            <%}%>
                                                        </select>
                                                    </div>
                                                </div> 
                                                <div class="modal-body">

                                                </div>

                                            </div>
                                            <div class="col-6">
                                                <div class="form-group row">
                                                    <div class="col-md-4">
                                                        <span>Job number*</span>
                                                    </div>
                                                    <div class="col-md-8">
                                                        <input type="text" required="" id="first-name" class="form-control" name="jno" value="<%=checkInput.checkValue(request.getParameter("jno"))%>" placeholder="Job number">
                                                    </div>
                                                </div>
                                                <div class="form-group row">
                                                    <div class="col-md-4">                                                    
                                                        <span>Job date *</span></div>
                                                    <div class="col-md-8">
                                                        <input type="text" required readonly id="expirydate" name="jobdate" value="<%=checkInput.checkValue(request.getParameter("jobdate"))%>" class="form-control pickadate1" placeholder="Job Date">
                                                    </div>
                                                </div>

                                                <div class="form-group row">
                                                    <div class="col-md-4">
                                                        <!--                                                        <span>Select fill</span>-->
                                                    </div>
                                                    <div class="col-md-8">
                                                        <div class="col-sm-12">
                                                            <ul class="list-unstyled mb-0">
                                                                <li class="d-inline-block mr-2">
                                                                    <fieldset>
                                                                        <div class="custom-control custom-checkbox">
                                                                            <input type="checkbox" class="custom-control-input" value="selectfill"  name="selectfill" id="customCheck3">
                                                                            <label class="custom-control-label" for="customCheck3">Select Fill</label>
                                                                        </div>
                                                                    </fieldset></li>
                                                                <li class="d-inline-block mr-2">
                                                                    <fieldset>
                                                                        <div class="custom-control custom-checkbox">
                                                                            <input type="checkbox" class="custom-control-input"  value="haulOff" name="haulOff" id="customCheck1">
                                                                            <label class="custom-control-label" for="customCheck1">Haul Off </label>
                                                                        </div>
                                                                    </fieldset></li>

                                                                <li class="d-inline-block mr-2">
                                                                    <fieldset>
                                                                        <div class="custom-control custom-checkbox">
                                                                            <input type="checkbox" class="custom-control-input" value="haulBack"  name="haulBack" id="customCheck2">
                                                                            <label class="custom-control-label" for="customCheck2">Haul Back</label>
                                                                        </div>
                                                                    </fieldset></li>
                                                            </ul>
                                                        </div> 
                                                    </div>
                                                </div>
                                                <div class="form-group row">
                                                    <div class="col-md-4">
                                                        <span>Others</span>
                                                    </div>
                                                    <div class="col-md-8">
                                                        <input type="text"  id="pincode" class="form-control" name="others" value="<%=checkInput.checkValue(request.getParameter("others"))%>" placeholder="Others">
                                                    </div>
                                                </div>
                                                <div class="form-group row">
                                                    <div class="col-md-4">
                                                        <span>Notes</span>
                                                    </div>
                                                    <div class="col-md-8">
                                                        <textarea class="form-control" name="notes"><%=checkInput.checkValue(request.getParameter("notes"))%> </textarea>
                                                    </div>
                                                </div>


                                            </div>
                                            <div class="col-md-8 offset-md-4">
                                                <button type="submit" class="btn btn-primary mr-1 mb-1">Save Information</button>
                                                <a href="<%= request.getContextPath()%>/job/List" class="btn btn-danger mr-1 mb-1 waves-effect waves-light">Cancel</a>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- // Basic Horizontal form layout section end -->



    </div>
</div>

<!-- END: Content-->
<jsp:include page="../Template/pageEnd.jsp"></jsp:include>    

<jsp:include page="../Template/footer.jsp"></jsp:include>
    <script>
        $(document).ready(function () {
            $(".pickadate1").pickadate({
                format: "mm-dd-yyyy"
            });
        });

        function searchAddress() {
            var id = $('#customer').val();
            $.ajax({
                method: "GET",
                url: "<%=request.getContextPath()%>/job/searchAddress/" + id,
                success: function (data) {
                    console.log(data);
                    $('.modal-body').html(data);
                }
            });
        }

</script>

<script src="<%=request.getContextPath()%>/assets-new/app-assets/vendors/js/pickers/pickadate/picker.js"></script>
<script src="<%=request.getContextPath()%>/assets-new/app-assets/vendors/js/pickers/pickadate/picker.date.js"></script>
<script src="<%=request.getContextPath()%>/assets-new/app-assets/vendors/js/pickers/pickadate/picker.time.js"></script>
<script src="<%=request.getContextPath()%>/assets-new/app-assets/vendors/js/pickers/pickadate/legacy.js"></script>

<script src="<%=request.getContextPath()%>/assets-new/app-assets/js/scripts/pickers/dateTime/pick-a-datetime.js"></script>