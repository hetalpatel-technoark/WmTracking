<%@page import="com.wmtrucking.utils.DateUtils"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.wmtrucking.utils.CheckInput"%>
<%@page import="com.wmtrucking.entities.MaCustomer"%>
<%@page import="com.wmtrucking.entities.MaDriver"%>

<%@page import="java.util.List"%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/assets-new/app-assets/vendors/css/pickers/pickadate/pickadate.css">  
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/assets-new/app-assets/vendors/css/forms/select/select2.min.css">

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
                                    <input type="hidden" name="loding_lat_txt" id="loding_lat_txt">
                                    <input type="hidden" name="loding_log_txt" id="loding_log_txt">
                                    <input type="hidden" name="dumping_lat_txt" id="dumping_lat_txt">
                                    <input type="hidden" name="dumping_log_txt" id="dumping_log_txt">
                                    <div class="form-body">
                                        <div class="row">
                                            <div class="col-6">
                                                <div class="form-group row">
                                                    <div class="col-md-4">
                                                        <span>Customer / Company </span>
                                                    </div>
                                                    <div class="col-md-8">
                                                        <!--                                                        <select  onchange="searchAddress()" id="customer" class="form-control select-class" name="customer" required>-->
                                                        <select class="select2 form-control"  name="customer"  multiple="multiple">

                                                            <%
                                                                List<MaCustomer> maCustomers = (List<MaCustomer>) request.getAttribute("maCustomer");
                                                                for (MaCustomer maCustomer : maCustomers) {
                                                            %>
                                                            <option <%=maCustomer.getId().toString().equals(checkInput.checkValue(request.getParameter("customer"))) ? "selected" : ""%>  value="<%=maCustomer.getId()%>"><%= maCustomer.getFirstname()%></option>
                                                            <%}%>
                                                        </select>
                                                    </div>
                                                </div> 
                                                <div class="form-group row">
                                                    <div class="col-md-4">
                                                        <span>Total Dumps * </span>
                                                    </div>
                                                    <div class="col-md-8">
                                                        <select name="count" required="" class="select2 form-control" value="<%=checkInput.checkValue(request.getParameter("count"))%>">                                                          
                                                            <option disabled="">Select Total Dumps </option>
                                                            <% for (int i = 1; i <= 1000; i++) {%>
                                                            <option><%= i%> </option>
                                                            <%}%>   
                                                        </select>
                                                    </div>
                                                </div>

                                                <div class="form-group row">
                                                    <div class="col-md-4">
                                                        <span>Loading Site Address *</span>
                                                    </div>
                                                    <div class="col-md-8">
                                                        <input type="text" required="" id="loding" onFocus="geolocate('load')"  class="form-control" name="lodingAddress" value="<%=checkInput.checkValue(request.getParameter("lodingAddress"))%>"  placeholder="Loading Site Address">
                                                    </div>
                                                </div>
                                                <div class="form-group row">
                                                    <div class="col-md-4">
                                                        <span>Dumping Site Address *</span>
                                                    </div>
                                                    <div class="col-md-8">
                                                        <input type="text" required id="dumping" onFocus="geolocate('dump')"  class="form-control" name="DumpingAddress" value="<%=checkInput.checkValue(request.getParameter("DumpingAddress"))%>"  placeholder="Dumping Site Address">
                                                    </div>
                                                </div>

                                                <div class="form-group row">
                                                    <div class="col-md-4">
                                                    </div>
                                                    <div class="col-md-8">
                                                        <div class="col-sm-12" >
                                                            <ul class="list-unstyled mb-0">

                                                                <li class="d-inline-block mr-2">
                                                                    <fieldset>
                                                                        <div class="custom-control custom-checkbox">
                                                                            <input type="checkbox" class="custom-control-input" value="true"  name="Sand" id="customCheck1">
                                                                            <label class="custom-control-label" for="customCheck1">Sand</label>
                                                                        </div>
                                                                    </fieldset></li>
                                                                <li class="d-inline-block mr-2">
                                                                    <fieldset>
                                                                        <div class="custom-control custom-checkbox">
                                                                            <input type="checkbox" class="custom-control-input" value="true"  name="selectfill" id="customCheck2">
                                                                            <label class="custom-control-label" for="customCheck2">Fill</label>
                                                                        </div>
                                                                    </fieldset></li>
                                                                <li class="d-inline-block mr-2">
                                                                    <fieldset>
                                                                        <div class="custom-control custom-checkbox">
                                                                            <input type="checkbox" class="custom-control-input"  value="true" name="haulOff" id="customCheck3">
                                                                            <label class="custom-control-label" for="customCheck3">Haul Off </label>
                                                                        </div>
                                                                    </fieldset></li>

                                                                <li class="d-inline-block mr-2">
                                                                    <fieldset>
                                                                        <div class="custom-control custom-checkbox">
                                                                            <input type="checkbox" class="custom-control-input" value="true"  name="haulBack" id="customCheck4">
                                                                            <label class="custom-control-label" for="customCheck4">Haul Back</label>
                                                                        </div>
                                                                    </fieldset></li>
                                                                <li class="d-inline-block mr-2">
                                                                    <fieldset>
                                                                        <div class="custom-control custom-checkbox">
                                                                            <input type="checkbox" class="custom-control-input" value="true"  name="common" id="customCheck5">
                                                                            <label class="custom-control-label" for="customCheck5">Common</label>
                                                                        </div>
                                                                    </fieldset></li>
                                                                <li class="d-inline-block mr-2">
                                                                    <fieldset>
                                                                        <div class="custom-control custom-checkbox">
                                                                            <input type="checkbox" class="custom-control-input" value="true"  name="hourly" id="customCheck6">
                                                                            <label class="custom-control-label" for="customCheck6">Hourly</label>
                                                                        </div>
                                                                    </fieldset></li>
                                                            </ul>
                                                        </div> 
                                                    </div>
                                                </div>


                                            </div>
                                            <div class="col-6">
                                                <div class="form-group row">
                                                    <div class="col-md-4">
                                                        <span>Job Number *</span>
                                                    </div>
                                                    <div class="col-md-8">
                                                        <input type="text" required  class="form-control" name="jno" value="<%=checkInput.checkValue(request.getParameter("jno"))%>" placeholder="Job Number">
                                                    </div>
                                                </div>
                                                <div class="form-group row">
                                                    <div class="col-md-4">
                                                        <span>Job Name *</span>
                                                    </div>
                                                    <div class="col-md-8">
                                                        <input type="text" required="" class="form-control" name="jname" value="<%=checkInput.checkValue(request.getParameter("jname"))%>" placeholder="Job Name">
                                                    </div>
                                                </div>
                                                <div class="form-group row">
                                                    <div class="col-md-4">                                                    
                                                        <span>Job Date *</span></div>
                                                    <div class="col-md-8">
                                                        <input type="text" required readonly name="jobdate" value="<%=request.getParameter("jobdate") != null ? request.getParameter("jobdate") : new DateUtils().dateWithFormat(new Date(), "MMMM dd, yyyy")%>"  class="form-control pickadate1" placeholder="Job Date">
                                                    </div>
                                                </div>


                                                <div class="form-group row">
                                                    <div class="col-md-4">
                                                        <span>Others</span>
                                                    </div>
                                                    <div class="col-md-8">
                                                        <input type="text" class="form-control" name="others" value="<%=checkInput.checkValue(request.getParameter("others"))%>" placeholder="Others">
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
                                                <div class="form-group row">
                                                    <div class="col-md-4">                                                    
                                                        <span>Job Created Date </span></div>
                                                    <div class="col-md-8">
                                                        <input type="text"  disabled=""  value="<%= new DateUtils().dateWithFormat(new Date(), "MMMM dd, yyyy")%>"  class="form-control " placeholder="Job Date">
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

    </div>
</div>

<!-- END: Content-->
<jsp:include page="../Template/pageEnd.jsp"></jsp:include>    

<jsp:include page="../Template/footer.jsp"></jsp:include>
<script src="<%=request.getContextPath()%>/assets-new/app-assets/vendors/js/forms/select/select2.full.min.js"></script>
<script>


                                                            $(document).ready(function () {
                                                                $(".pickadate1").pickadate({
                                                                    format: "mmmm dd, yyyy"
                                                                });
                                                            });

</script>


<script src="<%=request.getContextPath()%>/assets-new/app-assets/js/scripts/forms/select/form-select2.js"></script>

<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBnGTbyaxe2OyVmvs6A430GSxQftAARsH0&libraries=places&callback=initAutocomplete&type=address" async defer></script>
<script>
                                                            var autocomplete, autocomplete2;
                                                            function place_changed(gmap) {
                                                                console.log(gmap);
                                                            }
                                                            function initAutocomplete() {
                                                                autocomplete = new google.maps.places.Autocomplete((document.getElementById('loding')),
                                                                        {types: ['establishment']});
                                                                autocomplete.addListener('place_changed', fill_loding);
                                                                autocomplete.setOptions({strictBounds: false});

                                                                autocomplete2 = new google.maps.places.Autocomplete((document.getElementById('dumping')),
                                                                        {types: ['establishment']});
                                                                autocomplete2.addListener('place_changed', fill_dumping);
                                                                autocomplete2.setOptions({strictBounds: false});
                                                            }


                                                            function fill_loding() {

                                                                var lat = autocomplete.getPlace().geometry.location.lat();
                                                                var lng = autocomplete.getPlace().geometry.location.lng();
                                                                $('#loding_lat_txt').val(lat);
                                                                $('#loding_log_txt').val(lng);
                                                                console.log("loding.latitude." + lat);
                                                                console.log("loding.logitude." + lng);

                                                            }
                                                            function fill_dumping() {
                                                                var lat = autocomplete2.getPlace().geometry.location.lat();
                                                                var lng = autocomplete2.getPlace().geometry.location.lng();
                                                                $('#dumping_lat_txt').val(autocomplete2.getPlace().geometry.location.lat());
                                                                $('#dumping_log_txt').val(autocomplete2.getPlace().geometry.location.lng());
                                                                console.log("dumping.latitude." + lat);
                                                                console.log("dumping.logitude." + lng);
                                                            }
                                                            function geolocate(status) {
                                                                if (navigator.geolocation) {
                                                                    navigator.geolocation.getCurrentPosition(function (position) {
                                                                        var geolocation = {
                                                                            lat: position.coords.latitude,
                                                                            lng: position.coords.longitude
                                                                        };
                                                                        var circle = new google.maps.Circle({
                                                                            center: geolocation,
                                                                            radius: position.coords.accuracy
                                                                        });

                                                                        //autocomplete.setBounds(geolocation.getCurrentPosition());
                                                                        // autocomplete.setBounds(circle.getBounds());
                                                                        // autocomplete2.setBounds(circle.getBounds());
                                                                    });
                                                                }
                                                            }


</script>
<!--    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>-->
<!--    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>-->
<!--    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">-->
<!--    <script src="auto-complete.js"></script>-->

<script src="<%=request.getContextPath()%>/assets-new/app-assets/vendors/js/pickers/pickadate/picker.js"></script>
<script src="<%=request.getContextPath()%>/assets-new/app-assets/vendors/js/pickers/pickadate/picker.date.js"></script>
<script src="<%=request.getContextPath()%>/assets-new/app-assets/vendors/js/pickers/pickadate/picker.time.js"></script>
<script src="<%=request.getContextPath()%>/assets-new/app-assets/vendors/js/pickers/pickadate/legacy.js"></script>
<script src="<%=request.getContextPath()%>/assets-new/app-assets/js/scripts/pickers/dateTime/pick-a-datetime.js"></script>
