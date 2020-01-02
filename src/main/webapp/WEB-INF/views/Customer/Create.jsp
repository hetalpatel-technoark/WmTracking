<%@page import="java.util.Iterator"%>
<%@page import="com.wmtrucking.utils.CheckInput"%>
<%@page import="java.util.List"%>
<jsp:include page="../Template/header.jsp"></jsp:include>

    <div class="content-wrapper">
        <div class="content-header row">
            <div class="content-header-left col-md-9 col-12 mb-2">
                <div class="row breadcrumbs-top">
                    <div class="col-12">
                        <h2 class="content-header-title float-left mb-0">Customers</h2>
                        <div class="breadcrumb-wrapper col-12">
                            <ol class="breadcrumb">
                                <li class="breadcrumb-item"><a href="<%= request.getContextPath()%>/Dashboard/Dashboard">Home</a>
                            </li>
                            <li class="breadcrumb-item"><a href="<%= request.getContextPath()%>/customer/customerList">Customers</a>
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
                                <form method="post"  class="form-horizontal bordered-row" id="user-form" action="<%=request.getContextPath()%>/customer/PostCreate">
                                    <div class="form-body">
                                        <div class="row">
                                            <div class="col-6">
                                                <div class="form-group row">
                                                    <div class="col-md-4">
                                                        <span>First Name*</span>
                                                    </div>
                                                    <div class="col-md-8">
                                                        <input type="text" required="" id="first-name" class="form-control" name="fname" value="<%=checkInput.checkValue(request.getParameter("fname"))%>" placeholder="First Name">
                                                    </div>
                                                </div>
                                                <div class="form-group row">
                                                    <div class="col-md-4">
                                                        <span>Middle Name</span>
                                                    </div>
                                                    <div class="col-md-8">
                                                        <input type="text"  id="first-name" class="form-control" name="mname" value="<%=checkInput.checkValue(request.getParameter("mname"))%>" placeholder="Middle Name">
                                                    </div>
                                                </div>
                                                <div class="form-group row">
                                                    <div class="col-md-4">
                                                        <span>Last Name</span>
                                                    </div>
                                                    <div class="col-md-8">
                                                        <input type="text" id="first-name" class="form-control" name="lname" value="<%=checkInput.checkValue(request.getParameter("lname"))%>" placeholder="Last Name">
                                                    </div>
                                                </div>


                                                <div class="form-group row">
                                                    <div class="col-md-4">
                                                        <span>Company Name</span>
                                                    </div>
                                                    <div class="col-md-8">
                                                        <input type="text" id="first-name" class="form-control" name="cmpname" value="<%=checkInput.checkValue(request.getParameter("cmpname"))%>" placeholder="Company name">
                                                    </div>
                                                </div>

                                                <div class="form-group row">
                                                    <div class="col-md-4">
                                                        <span>Phone Number*</span>
                                                    </div>
<!--                                                    <div class="col-md-2">
                                                        <select name="countryCode" class="form-control" id="">
                                                            <option data-countryCode="GB" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+44") ? "Selected" : ""%>  value="+" >UK (+44)</option>
                                                            <option data-countryCode="US" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+1") ? "Selected" : ""%>  value="+1">USA (+1)</option>
                                                            <optgroup label="Other countries">
                                                                <option data-countryCode="DZ" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+213") ? "Selected" : ""%> value="+213">Algeria (+213)</option>
                                                                <option data-countryCode="AD" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+376") ? "Selected" : ""%> value="+376">Andorra (+376)</option>
                                                                <option data-countryCode="AO" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+244") ? "Selected" : ""%> value="+244">Angola (+244)</option>
                                                                <option data-countryCode="AI" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+1264") ? "Selected" : ""%> value="+1264">Anguilla (+1264)</option>
                                                                <option data-countryCode="AG" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+1268") ? "Selected" : ""%> value="+1268">Antigua &amp; Barbuda (+1268)</option>
                                                                <option data-countryCode="AR" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+54") ? "Selected" : ""%> value="+54">Argentina (+54)</option>
                                                                <option data-countryCode="AM" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+374") ? "Selected" : ""%> value="+374">Armenia (+374)</option>
                                                                <option data-countryCode="AW" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+297") ? "Selected" : ""%> value="+297">Aruba (+297)</option>
                                                                <option data-countryCode="AU" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+61") ? "Selected" : ""%> value="+61">Australia (+61)</option>
                                                                <option data-countryCode="AT" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+43") ? "Selected" : ""%> value="+43">Austria (+43)</option>
                                                                <option data-countryCode="AZ" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+994") ? "Selected" : ""%> value="+994">Azerbaijan (+994)</option>
                                                                <option data-countryCode="BS" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+1242") ? "Selected" : ""%> value="+1242">Bahamas (+1242)</option>
                                                                <option data-countryCode="BH" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+973") ? "Selected" : ""%> value="+973">Bahrain (+973)</option>
                                                                <option data-countryCode="BD" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+880") ? "Selected" : ""%> value="+880">Bangladesh (+880)</option>
                                                                <option data-countryCode="BB" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+1246") ? "Selected" : ""%> value="+1246">Barbados (+1246)</option>
                                                                <option data-countryCode="BY" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+375") ? "Selected" : ""%> value="+375">Belarus (+375)</option>
                                                                <option data-countryCode="BE" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+32") ? "Selected" : ""%> value="+32">Belgium (+32)</option>
                                                                <option data-countryCode="BZ" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+501") ? "Selected" : ""%> value="+501">Belize (+501)</option>
                                                                <option data-countryCode="BJ" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+229") ? "Selected" : ""%> value="+229">Benin (+229)</option>
                                                                <option data-countryCode="BM" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+1441") ? "Selected" : ""%> value="+1441">Bermuda (+1441)</option>
                                                                <option data-countryCode="BT" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+975") ? "Selected" : ""%> value="+975">Bhutan (+975)</option>
                                                                <option data-countryCode="BO" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+591") ? "Selected" : ""%> value="+591">Bolivia (+591)</option>
                                                                <option data-countryCode="BA" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+387") ? "Selected" : ""%> value="+387">Bosnia Herzegovina (+387)</option>
                                                                <option data-countryCode="BW" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+267") ? "Selected" : ""%> value="+267">Botswana (+267)</option>
                                                                <option data-countryCode="BR" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+55") ? "Selected" : ""%> value="+55">Brazil (+55)</option>
                                                                <option data-countryCode="BN" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+673") ? "Selected" : ""%> value="+673">Brunei (+673)</option>
                                                                <option data-countryCode="BG" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+359") ? "Selected" : ""%> value="+359">Bulgaria (+359)</option>
                                                                <option data-countryCode="BF" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+226") ? "Selected" : ""%> value="+226">Burkina Faso (+226)</option>
                                                                <option data-countryCode="BI" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+257") ? "Selected" : ""%> value="+257">Burundi (+257)</option>
                                                                <option data-countryCode="KH" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+855") ? "Selected" : ""%> value="+855">Cambodia (+855)</option>
                                                                <option data-countryCode="CM" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+237") ? "Selected" : ""%> value="+237">Cameroon (+237)</option>
                                                                <option data-countryCode="CA" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+1") ? "Selected" : ""%> value="+1">Canada (+1)</option>
                                                                <option data-countryCode="CV" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+238") ? "Selected" : ""%> value="+238">Cape Verde Islands (+238)</option>
                                                                <option data-countryCode="KY" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+1345") ? "Selected" : ""%> value="+1345">Cayman Islands (+1345)</option>
                                                                <option data-countryCode="CF" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+236") ? "Selected" : ""%> value="+236">Central African Republic (+236)</option>
                                                                <option data-countryCode="CL" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+56") ? "Selected" : ""%> value="+56">Chile (+56)</option>
                                                                <option data-countryCode="CN" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+86") ? "Selected" : ""%> value="+86">China (+86)</option>
                                                                <option data-countryCode="CO" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+57") ? "Selected" : ""%> value="+57">Colombia (+57)</option>
                                                                <option data-countryCode="KM" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+269") ? "Selected" : ""%> value="+269">Comoros (+269)</option>
                                                                <option data-countryCode="CG" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+242") ? "Selected" : ""%> value="+242">Congo (+242)</option>
                                                                <option data-countryCode="CK" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+682") ? "Selected" : ""%> value="+682">Cook Islands (+682)</option>
                                                                <option data-countryCode="CR" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+506") ? "Selected" : ""%> value="+506">Costa Rica (+506)</option>
                                                                <option data-countryCode="HR" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+385") ? "Selected" : ""%> value="+385">Croatia (+385)</option>
                                                                <option data-countryCode="CU" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+53") ? "Selected" : ""%> value="+53">Cuba (+53)</option>
                                                                <option data-countryCode="CY" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+90392") ? "Selected" : ""%> value="+90392">Cyprus North (+90392)</option>
                                                                <option data-countryCode="CY" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+357") ? "Selected" : ""%> value="+357">Cyprus South (+357)</option>
                                                                <option data-countryCode="CZ" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+42") ? "Selected" : ""%> value="+42">Czech Republic (+42)</option>
                                                                <option data-countryCode="DK" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+45") ? "Selected" : ""%> value="+45">Denmark (+45)</option>
                                                                <option data-countryCode="DJ" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+253") ? "Selected" : ""%> value="+253">Djibouti (+253)</option>
                                                                <option data-countryCode="DM" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+1809") ? "Selected" : ""%> value="+1809">Dominica (+1809)</option>
                                                                <option data-countryCode="DO" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+1809") ? "Selected" : ""%> value="+1809">Dominican Republic (+1809)</option>
                                                                <option data-countryCode="EC" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+593") ? "Selected" : ""%> value="+593">Ecuador (+593)</option>
                                                                <option data-countryCode="EG" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+20") ? "Selected" : ""%> value="+20">Egypt (+20)</option>
                                                                <option data-countryCode="SV" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+503") ? "Selected" : ""%> value="+503">El Salvador (+503)</option>
                                                                <option data-countryCode="GQ" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+240") ? "Selected" : ""%> value="+240">Equatorial Guinea (+240)</option>
                                                                <option data-countryCode="ER" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+291") ? "Selected" : ""%> value="+291">Eritrea (+291)</option>
                                                                <option data-countryCode="EE" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+372") ? "Selected" : ""%> value="+372">Estonia (+372)</option>
                                                                <option data-countryCode="ET" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+251") ? "Selected" : ""%> value="+251">Ethiopia (+251)</option>
                                                                <option data-countryCode="FK" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+500") ? "Selected" : ""%> value="+500">Falkland Islands (+500)</option>
                                                                <option data-countryCode="FO" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+298") ? "Selected" : ""%> value="+298">Faroe Islands (+298)</option>
                                                                <option data-countryCode="FJ" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+679") ? "Selected" : ""%> value="+679">Fiji (+679)</option>
                                                                <option data-countryCode="FI" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+358") ? "Selected" : ""%> value="+358">Finland (+358)</option>
                                                                <option data-countryCode="FR" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+33") ? "Selected" : ""%> value="+33">France (+33)</option>
                                                                <option data-countryCode="GF" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+594") ? "Selected" : ""%> value="+594">French Guiana (+594)</option>
                                                                <option data-countryCode="PF" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+689") ? "Selected" : ""%> value="+689">French Polynesia (+689)</option>
                                                                <option data-countryCode="GA" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+241") ? "Selected" : ""%> value="+241">Gabon (+241)</option>
                                                                <option data-countryCode="GM" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+220") ? "Selected" : ""%> value="+220">Gambia (+220)</option>
                                                                <option data-countryCode="GE" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+7880") ? "Selected" : ""%> value="+7880">Georgia (+7880)</option>
                                                                <option data-countryCode="DE" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+49") ? "Selected" : ""%> value="+49">Germany (+49)</option>
                                                                <option data-countryCode="GH" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+233") ? "Selected" : ""%> value="+233">Ghana (+233)</option>
                                                                <option data-countryCode="GI" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+350") ? "Selected" : ""%> value="+350">Gibraltar (+350)</option>
                                                                <option data-countryCode="GR" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+30") ? "Selected" : ""%> value="+30">Greece (+30)</option>
                                                                <option data-countryCode="GL" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+299") ? "Selected" : ""%> value="+299">Greenland (+299)</option>
                                                                <option data-countryCode="GD" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+1473") ? "Selected" : ""%> value="+1473">Grenada (+1473)</option>
                                                                <option data-countryCode="GP" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+590") ? "Selected" : ""%> value="+590">Guadeloupe (+590)</option>
                                                                <option data-countryCode="GU" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+671") ? "Selected" : ""%> value="+671">Guam (+671)</option>
                                                                <option data-countryCode="GT" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+502") ? "Selected" : ""%> value="+502">Guatemala (+502)</option>
                                                                <option data-countryCode="GN" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+224") ? "Selected" : ""%> value="+224">Guinea (+224)</option>
                                                                <option data-countryCode="GW" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+245") ? "Selected" : ""%> value="+245">Guinea - Bissau (+245)</option>
                                                                <option data-countryCode="GY" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+592") ? "Selected" : ""%> value="+592">Guyana (+592)</option>
                                                                <option data-countryCode="HT" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+509") ? "Selected" : ""%> value="+509">Haiti (+509)</option>
                                                                <option data-countryCode="HN" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+504") ? "Selected" : ""%> value="+504">Honduras (+504)</option>
                                                                <option data-countryCode="HK" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+852") ? "Selected" : ""%> value="+852">Hong Kong (+852)</option>
                                                                <option data-countryCode="HU" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+36") ? "Selected" : ""%> value="+36">Hungary (+36)</option>
                                                                <option data-countryCode="IS" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+35") ? "Selected" : ""%> value="+354">Iceland (+354)</option>
                                                                <option data-countryCode="IN" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+91") ? "Selected" : ""%> value="+91">India (+91)</option>
                                                                <option data-countryCode="ID" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+62") ? "Selected" : ""%> value="+62">Indonesia (+62)</option>
                                                                <option data-countryCode="IR" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+98") ? "Selected" : ""%> value="+98">Iran (+98)</option>
                                                                <option data-countryCode="IQ" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+964") ? "Selected" : ""%> value="+964">Iraq (+964)</option>
                                                                <option data-countryCode="IE" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+353") ? "Selected" : ""%> value="+353">Ireland (+353)</option>
                                                                <option data-countryCode="IL" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+972") ? "Selected" : ""%> value="+972">Israel (+972)</option>
                                                                <option data-countryCode="IT" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+39") ? "Selected" : ""%> value="+39">Italy (+39)</option>
                                                                <option data-countryCode="JM" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+1876") ? "Selected" : ""%> value="+1876">Jamaica (+1876)</option>
                                                                <option data-countryCode="JP" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+81") ? "Selected" : ""%> value="+81">Japan (+81)</option>
                                                                <option data-countryCode="JO" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+962") ? "Selected" : ""%> value="+962">Jordan (+962)</option>
                                                                <option data-countryCode="KZ" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+7") ? "Selected" : ""%> value="+7">Kazakhstan (+7)</option>
                                                                <option data-countryCode="KE" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+254") ? "Selected" : ""%> value="+254">Kenya (+254)</option>
                                                                <option data-countryCode="KI" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+686") ? "Selected" : ""%> value="+686">Kiribati (+686)</option>
                                                                <option data-countryCode="KP" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+850") ? "Selected" : ""%> value="+850">Korea North (+850)</option>
                                                                <option data-countryCode="KR" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+82") ? "Selected" : ""%> value="+82">Korea South (+82)</option>
                                                                <option data-countryCode="KW" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+965") ? "Selected" : ""%> value="+965">Kuwait (+965)</option>
                                                                <option data-countryCode="KG" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+996") ? "Selected" : ""%> value="+996">Kyrgyzstan (+996)</option>
                                                                <option data-countryCode="LA" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+856") ? "Selected" : ""%> value="+856">Laos (+856)</option>
                                                                <option data-countryCode="LV" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+371") ? "Selected" : ""%> value="+371">Latvia (+371)</option>
                                                                <option data-countryCode="LB" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+961") ? "Selected" : ""%> value="+961">Lebanon (+961)</option>
                                                                <option data-countryCode="LS" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+266") ? "Selected" : ""%> value="+266">Lesotho (+266)</option>
                                                                <option data-countryCode="LR" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+231") ? "Selected" : ""%> value="+231">Liberia (+231)</option>
                                                                <option data-countryCode="LY" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+218") ? "Selected" : ""%> value="+218">Libya (+218)</option>
                                                                <option data-countryCode="LI" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+417") ? "Selected" : ""%> value="+417">Liechtenstein (+417)</option>
                                                                <option data-countryCode="LT" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+370") ? "Selected" : ""%> value="+370">Lithuania (+370)</option>
                                                                <option data-countryCode="LU" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+352") ? "Selected" : ""%> value="+352">Luxembourg (+352)</option>
                                                                <option data-countryCode="MO" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+853") ? "Selected" : ""%> value="+853">Macao (+853)</option>
                                                                <option data-countryCode="MK" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+389") ? "Selected" : ""%> value="+389">Macedonia (+389)</option>
                                                                <option data-countryCode="MG" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+261") ? "Selected" : ""%> value="+261">Madagascar (+261)</option>
                                                                <option data-countryCode="MW" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+265") ? "Selected" : ""%> value="+265">Malawi (+265)</option>
                                                                <option data-countryCode="MY" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+60") ? "Selected" : ""%> value="+60">Malaysia (+60)</option>
                                                                <option data-countryCode="MV" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+960") ? "Selected" : ""%> value="+960">Maldives (+960)</option>
                                                                <option data-countryCode="ML" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+223") ? "Selected" : ""%> value="+223">Mali (+223)</option>
                                                                <option data-countryCode="MT" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+356") ? "Selected" : ""%> value="+356">Malta (+356)</option>
                                                                <option data-countryCode="MH" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+692") ? "Selected" : ""%> value="+692">Marshall Islands (+692)</option>
                                                                <option data-countryCode="MQ" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+596") ? "Selected" : ""%> value="+596">Martinique (+596)</option>
                                                                <option data-countryCode="MR" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+222") ? "Selected" : ""%> value="+222">Mauritania (+222)</option>
                                                                <option data-countryCode="YT" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+269") ? "Selected" : ""%> value="+269">Mayotte (+269)</option>
                                                                <option data-countryCode="MX" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+52") ? "Selected" : ""%> value="+52">Mexico (+52)</option>
                                                                <option data-countryCode="FM" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+691") ? "Selected" : ""%> value="+691">Micronesia (+691)</option>
                                                                <option data-countryCode="MD" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+373") ? "Selected" : ""%> value="+373">Moldova (+373)</option>
                                                                <option data-countryCode="MC" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+377") ? "Selected" : ""%> value="+377">Monaco (+377)</option>
                                                                <option data-countryCode="MN" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+976") ? "Selected" : ""%> value="+976">Mongolia (+976)</option>
                                                                <option data-countryCode="MS" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+1664") ? "Selected" : ""%> value="+1664">Montserrat (+1664)</option>
                                                                <option data-countryCode="MA" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+212") ? "Selected" : ""%> value="+212">Morocco (+212)</option>
                                                                <option data-countryCode="MZ" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+258") ? "Selected" : ""%> value="+258">Mozambique (+258)</option>
                                                                <option data-countryCode="MN" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+95") ? "Selected" : ""%> value="+95">Myanmar (+95)</option>
                                                                <option data-countryCode="NA" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+264") ? "Selected" : ""%> value="+264">Namibia (+264)</option>
                                                                <option data-countryCode="NR" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+674") ? "Selected" : ""%> value="+674">Nauru (+674)</option>
                                                                <option data-countryCode="NP" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+977") ? "Selected" : ""%> value="+977">Nepal (+977)</option>
                                                                <option data-countryCode="NL" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+31") ? "Selected" : ""%> value="+31">Netherlands (+31)</option>
                                                                <option data-countryCode="NC" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+687") ? "Selected" : ""%> value="+687">New Caledonia (+687)</option>
                                                                <option data-countryCode="NZ" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+64") ? "Selected" : ""%> value="+64">New Zealand (+64)</option>
                                                                <option data-countryCode="NI" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+505") ? "Selected" : ""%> value="+505">Nicaragua (+505)</option>
                                                                <option data-countryCode="NE" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+227") ? "Selected" : ""%> value="+227">Niger (+227)</option>
                                                                <option data-countryCode="NG" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+234") ? "Selected" : ""%> value="+234">Nigeria (+234)</option>
                                                                <option data-countryCode="NU" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+683") ? "Selected" : ""%> value="+683">Niue (+683)</option>
                                                                <option data-countryCode="NF" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+672") ? "Selected" : ""%> value="+672">Norfolk Islands (+672)</option>
                                                                <option data-countryCode="NP" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+670") ? "Selected" : ""%> value="+670">Northern Marianas (+670)</option>
                                                                <option data-countryCode="NO" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+47") ? "Selected" : ""%> value="+47">Norway (+47)</option>
                                                                <option data-countryCode="OM" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+968") ? "Selected" : ""%> value="+968">Oman (+968)</option>
                                                                <option data-countryCode="PW" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+680") ? "Selected" : ""%> value="+680">Palau (+680)</option>
                                                                <option data-countryCode="PA" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+507") ? "Selected" : ""%> value="+507">Panama (+507)</option>
                                                                <option data-countryCode="PG" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+675") ? "Selected" : ""%> value="+675">Papua New Guinea (+675)</option>
                                                                <option data-countryCode="PY" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+595") ? "Selected" : ""%> value="+595">Paraguay (+595)</option>
                                                                <option data-countryCode="PE" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+51") ? "Selected" : ""%> value="+51">Peru (+51)</option>
                                                                <option data-countryCode="PH" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+63") ? "Selected" : ""%> value="+63">Philippines (+63)</option>
                                                                <option data-countryCode="PL" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+48") ? "Selected" : ""%> value="+48">Poland (+48)</option>
                                                                <option data-countryCode="PT" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+351") ? "Selected" : ""%> value="+351">Portugal (+351)</option>
                                                                <option data-countryCode="PR" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+1787") ? "Selected" : ""%> value="+1787">Puerto Rico (+1787)</option>
                                                                <option data-countryCode="QA" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+974") ? "Selected" : ""%> value="+974">Qatar (+974)</option>
                                                                <option data-countryCode="RE" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+262") ? "Selected" : ""%> value="+262">Reunion (+262)</option>
                                                                <option data-countryCode="RO" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+40") ? "Selected" : ""%> value="+40">Romania (+40)</option>
                                                                <option data-countryCode="RU" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+7") ? "Selected" : ""%> value="+7">Russia (+7)</option>
                                                                <option data-countryCode="RW" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+250") ? "Selected" : ""%> value="+250">Rwanda (+250)</option>
                                                                <option data-countryCode="SM" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+378") ? "Selected" : ""%> value="+378">San Marino (+378)</option>
                                                                <option data-countryCode="ST" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+239") ? "Selected" : ""%> value="+239">Sao Tome &amp; Principe (+239)</option>
                                                                <option data-countryCode="SA" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+966") ? "Selected" : ""%> value="+966">Saudi Arabia (+966)</option>
                                                                <option data-countryCode="SN" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+221") ? "Selected" : ""%> value="+221">Senegal (+221)</option>
                                                                <option data-countryCode="CS" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+381") ? "Selected" : ""%> value="+381">Serbia (+381)</option>
                                                                <option data-countryCode="SC" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+248") ? "Selected" : ""%> value="+248">Seychelles (+248)</option>
                                                                <option data-countryCode="SL" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+232") ? "Selected" : ""%> value="+232">Sierra Leone (+232)</option>
                                                                <option data-countryCode="SG" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+65") ? "Selected" : ""%> value="+65">Singapore (+65)</option>
                                                                <option data-countryCode="SK" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+421") ? "Selected" : ""%> value="+421">Slovak Republic (+421)</option>
                                                                <option data-countryCode="SI" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+386") ? "Selected" : ""%> value="+386">Slovenia (+386)</option>
                                                                <option data-countryCode="SB" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+677") ? "Selected" : ""%> value="+677">Solomon Islands (+677)</option>
                                                                <option data-countryCode="SO" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+252") ? "Selected" : ""%> value="+252">Somalia (+252)</option>
                                                                <option data-countryCode="ZA" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+27") ? "Selected" : ""%> value="+27">South Africa (+27)</option>
                                                                <option data-countryCode="ES" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+34") ? "Selected" : ""%> value="+34">Spain (+34)</option>
                                                                <option data-countryCode="LK" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+94") ? "Selected" : ""%> value="+94">Sri Lanka (+94)</option>
                                                                <option data-countryCode="SH" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+290") ? "Selected" : ""%> value="+290">St. Helena (+290)</option>
                                                                <option data-countryCode="KN" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+1869") ? "Selected" : ""%> value="+1869">St. Kitts (+1869)</option>
                                                                <option data-countryCode="SC" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+1758") ? "Selected" : ""%> value="+1758">St. Lucia (+1758)</option>
                                                                <option data-countryCode="SD" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+249") ? "Selected" : ""%> value="+249">Sudan (+249)</option>
                                                                <option data-countryCode="SR" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+597") ? "Selected" : ""%> value="+597">Suriname (+597)</option>
                                                                <option data-countryCode="SZ" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+268") ? "Selected" : ""%> value="+268">Swaziland (+268)</option>
                                                                <option data-countryCode="SE" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+46") ? "Selected" : ""%> value="+46">Sweden (+46)</option>
                                                                <option data-countryCode="CH" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+41") ? "Selected" : ""%> value="+41">Switzerland (+41)</option>
                                                                <option data-countryCode="SI" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+963") ? "Selected" : ""%> value="+963">Syria (+963)</option>
                                                                <option data-countryCode="TW" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+886") ? "Selected" : ""%> value="+886">Taiwan (+886)</option>
                                                                <option data-countryCode="TJ" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+7") ? "Selected" : ""%> value="+7">Tajikstan (+7)</option>
                                                                <option data-countryCode="TH" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+66") ? "Selected" : ""%> value="+66">Thailand (+66)</option>
                                                                <option data-countryCode="TG" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+228") ? "Selected" : ""%> value="+228">Togo (+228)</option>
                                                                <option data-countryCode="TO" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+676") ? "Selected" : ""%> value="+676">Tonga (+676)</option>
                                                                <option data-countryCode="TT" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+1868") ? "Selected" : ""%> value="+1868">Trinidad &amp; Tobago (+1868)</option>
                                                                <option data-countryCode="TN" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+216") ? "Selected" : ""%> value="+216">Tunisia (+216)</option>
                                                                <option data-countryCode="TR" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+90") ? "Selected" : ""%> value="+90">Turkey (+90)</option>
                                                                <option data-countryCode="TM" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+7") ? "Selected" : ""%> value="+7">Turkmenistan (+7)</option>
                                                                <option data-countryCode="TM" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+993") ? "Selected" : ""%> value="+993">Turkmenistan (+993)</option>
                                                                <option data-countryCode="TC" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+1649") ? "Selected" : ""%> value="+1649">Turks &amp; Caicos Islands (+1649)</option>
                                                                <option data-countryCode="TV" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+688") ? "Selected" : ""%> value="+688">Tuvalu (+688)</option>
                                                                <option data-countryCode="UG" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+256") ? "Selected" : ""%> value="+256">Uganda (+256)</option>
                                                                <option data-countryCode="UA" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+380") ? "Selected" : ""%> value="+380">Ukraine (+380)</option>
                                                                <option data-countryCode="AE" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+971") ? "Selected" : ""%> value="+971">United Arab Emirates (+971)</option>
                                                                <option data-countryCode="UY" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+598") ? "Selected" : ""%> value="+598">Uruguay (+598)</option>
                                                                <option data-countryCode="UZ" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+7") ? "Selected" : ""%> value="+7">Uzbekistan (+7)</option>
                                                                <option data-countryCode="VU" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+678") ? "Selected" : ""%> value="+678">Vanuatu (+678)</option>
                                                                <option data-countryCode="VA" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+379") ? "Selected" : ""%> value="+379">Vatican City (+379)</option>
                                                                <option data-countryCode="VE" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+58") ? "Selected" : ""%> value="+58">Venezuela (+58)</option>
                                                                <option data-countryCode="VN" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+84") ? "Selected" : ""%> value="+84">Vietnam (+84)</option>
                                                                <option data-countryCode="VG" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+1284") ? "Selected" : ""%> value="+1284">Virgin Islands - British (+1284)</option>
                                                                <option data-countryCode="VI" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+1340") ? "Selected" : ""%> value="+1340">Virgin Islands - US (+1340)</option>
                                                                <option data-countryCode="WF" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+681") ? "Selected" : ""%> value="+681">Wallis &amp; Futuna (+681)</option>
                                                                <option data-countryCode="YE" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+969") ? "Selected" : ""%> value="+969">Yemen (North)(+969)</option>
                                                                <option data-countryCode="YE" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+967") ? "Selected" : ""%> value="+967">Yemen (South)(+967)</option>
                                                                <option data-countryCode="ZM" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+260") ? "Selected" : ""%> value="+260">Zambia (+260)</option>
                                                                <option data-countryCode="ZW" <%=checkInput.checkValue(request.getParameter("countryCode")).equals("+263") ? "Selected" : ""%> value="+263">Zimbabwe (+263)</option>
                                                            </optgroup>
                                                        </select>                                                    

                                                    </div>-->

                                                    <div class="col-md-8">
                                                        <input type="text" required="" id="first-name" class="form-control" name="phone" value="<%=checkInput.checkValue(request.getParameter("phone"))%>" placeholder="Phone Number">
                                                    </div>
                                                </div>
                                                <div class="form-group row">
                                                    <div class="col-md-4">
                                                        <span>Email</span>
                                                    </div>
                                                    <div class="col-md-8">
                                                        <input type="email" id="first-name" class="form-control" name="email" value="<%=checkInput.checkValue(request.getParameter("email"))%>" placeholder="Email">
                                                    </div>
                                                </div>
                                                <div class="form-group row">
                                                    <div class="col-md-4">
                                                        <span>Status</span>
                                                    </div>
                                                    <div class="col-md-8">
                                                        <select class="form-control select-class" name="status" required>
                                                            <option value="Active">Active</option>
                                                            <option value="Inactive">Inactive</option>
                                                        </select>
                                                    </div>
                                                </div>

                                            </div>
                                            <div class="col-6">
                                                <div class="form-group row">
                                                    <div class="col-md-4">
                                                        <span>Address </span>
                                                    </div>
                                                    <div class="col-md-8">
                                                        <input type="text" id="first-name" class="form-control" name="add1" value="<%=checkInput.checkValue(request.getParameter("add1"))%>" placeholder="Address 1">
                                                    </div>
                                                </div>
                                                <!--                                                <div class="form-group row">
                                                                                                    <div class="col-md-4">
                                                                                                        <span>Address 2</span>
                                                                                                    </div>
                                                                                                    <div class="col-md-8">
                                                                                                        <input type="text" id="first-name" class="form-control" name="add2" value="<%=checkInput.checkValue(request.getParameter("add2"))%>" placeholder="Address 2">
                                                                                                    </div>
                                                                                                </div>
                                                
                                                                                                <div class="form-group row">
                                                                                                    <div class="col-md-4">
                                                                                                        <span>Address 3</span>
                                                                                                    </div>
                                                                                                    <div class="col-md-8">
                                                                                                        <input type="text" id="first-name" class="form-control" name="add3" value="<%=checkInput.checkValue(request.getParameter("add3"))%>" placeholder="Address 3">
                                                                                                    </div>
                                                                                                </div>-->


                                                <div class="form-group row">
                                                    <div class="col-md-4">
                                                        <span>City</span>
                                                    </div>
                                                    <div class="col-md-8">
                                                        <input type="text"  id="first-name" class="form-control" name="city" value="<%=checkInput.checkValue(request.getParameter("city"))%>" placeholder="City">
                                                    </div>
                                                </div>
                                                <div class="form-group row">
                                                    <div class="col-md-4">
                                                        <span>State</span>
                                                    </div>
                                                    <div class="col-md-8">
                                                        <input type="text" id="state" class="form-control" name="state" value="<%=checkInput.checkValue(request.getParameter("state"))%>" placeholder="State">
                                                    </div>
                                                </div>
                                                <!--                                                <div class="form-group row">
                                                                                                    <div class="col-md-4">
                                                                                                        <span>Country</span>
                                                                                                    </div>
                                                                                                    <div class="col-md-8">
                                                                                                        <input type="text" id="country" class="form-control" name="country" value="<%=checkInput.checkValue(request.getParameter("country"))%>" placeholder="Country">
                                                                                                    </div>
                                                                                                </div>-->
                                                <div class="form-group row">
                                                    <div class="col-md-4">
                                                        <span> Zipcode </span>
                                                    </div>
                                                    <div class="col-md-8">
                                                        <input type="number"  id="pincode" class="form-control" name="pin" value="<%=checkInput.checkValue(request.getParameter("pin"))%>" placeholder="Zipcode">
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-md-8 offset-md-4">
                                                <button type="submit" class="btn btn-primary mr-1 mb-1">Save Information</button>
                                                <a href="<%= request.getContextPath()%>/customer/customerList" class="btn btn-danger mr-1 mb-1 waves-effect waves-light">Cancel</a>
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
