
<%@page import="com.wmtrucking.utils.DateUtils"%>
<%@page import="com.wmtrucking.entities.MaInvoice"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:v="urn:schemas-microsoft-com:vml" xmlns:o="urn:schemas-microsoft-com:office:office">
    <head>
        <title>
            Equi360 Invoice
        </title>
        <meta charset="UTF-8" />
        <meta http-equiv="Cache-control" content="private" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <style type="text/css">
            html * {
                font-family: sans-serif !important;
            }

            table {
                border-collapse: collapse;
                width: 100%;
            }

            th,
            td {
                text-align: left;
                padding: 5px;
                border-collapse: collapse;
                background-clip: padding-box; /* Add this line */
            }
        </style>
        <!--[if !mso]><!-->
        <style type="text/css">
            @media only screen and (max-width:480px) {
                @-ms-viewport {
                    width: 320px;
                }
                @viewport {
                    width: 320px;
                }
            }

            .text-top {
                vertical-align: top;
            }
        </style>
        <style type="text/css">
            #outlook a {
                padding: 0;
            }

            .ReadMsgBody {
                width: 100%;
            }

            .ExternalClass {
                width: 100%;
            }

            .ExternalClass * {
                line-height: 100%;
            }

            body {
                margin: 0;
                padding: 0;
                -webkit-text-size-adjust: 100%;
                -ms-text-size-adjust: 100%;
            }

            table,
            td {
                border-collapse: collapse;
                mso-table-lspace: 0pt;
                mso-table-rspace: 0pt;
            }

            img {
                border: 0;
                height: auto;
                line-height: 100%;
                outline: none;
                text-decoration: none;
                -ms-interpolation-mode: bicubic;
            }

            p {
                display: block;
                margin: 13px 0;
            }
            .row:after {
                content: "";
                display: table;
                clear: both;
            }
        </style>
        <!--[if !mso]><!-->
        <style type="text/css">
            @media only screen and (max-width:480px) {
                @-ms-viewport {
                    width: 320px;
                }
                @viewport {
                    width: 320px;
                }
            }
        </style>
        <style type="text/css">
            @media only screen and (min-width:480px) {
                .mj-column-per-33 {
                    width: 33.333333%!important;
                }
                .mj-column-per-25 {
                    width: 25%!important;
                }
                .mj-column-per-100 {
                    width: 100%!important;
                }
            }
        </style>
        <style type="text/css">
            @media only screen and (min-width:480px) {
                .mj-column-per-33 {
                    width: 33.333333%!important;
                }
                .mj-column-per-25 {
                    width: 25%!important;
                }
                .mj-column-per-100 {
                    width: 100%!important;
                }
            }
            hr{ 
                height: 2px;
                color: #0c3b21;
                background-color: #0c3b21;
                border: none;
            }
        </style>
    </head>

    <body style="background: #FFFFFF;">
        <%
            MaInvoice invoices = (MaInvoice) request.getAttribute("maInvoice");
        %>
        <%if (invoices != null) {%>
        <table style="width: 100%;" width="100%">
            <tbody>
                <tr>
                    <td style="width: 23.33%;">
                        <img style="max-width:50%" src="<%=request.getContextPath()%>/assets-new/app-assets/images/CompLogo.jpg" alt="" width="160" style="height:47px;"/>
                    </td>

                    <td style="width: 23.33%; font-size: 10px;text-align: right;vertical-align: top;">                       
                    </td>
                </tr>
            </tbody>
        </table>        

        <table style="width: 100%;">
            <tbody>
                <tr>
                    <td style="width: 50%;font-size: 10px;">&nbsp;<strong>To</strong></td>
                    <td style="width: 50%; text-align: right;">
                        <p class="p2" style=" font-size: 10px;"><strong>From </strong> </p>
                    </td>
                </tr>
                <tr>
                    <td style="width: 50%;    font-size: 10px;">
                        <p> <%=(invoices.getToaddress() != null && !invoices.getToaddress().equals("")) ? invoices.getToaddress() : ""%></p>
                    </td>
                    <td style="width: 50%;">
                        <p class="p2" style="text-align: right;    font-size: 10px;display: inline-block;vertical-align: top;"><strong></strong><%= invoices.getFromaddress()%></p>
                    </td>
                </tr>
            </tbody>
        </table>
        <table style="color: white; width: 100%; background-color: #0c3b21; border-color: #0c3b21;">
            <tbody>
                <tr>
                    <td style="width: 100%; vertical-align: middle; font-size: 15px;">Job Number: <%=  invoices.getJobid().getJobnumber()%></td>
                </tr>
            </tbody>
        </table>
        <!--        <hr/>-->


        <div style="font-size: 10px;">
            <br/>
            <p class="p2" style=" font-size: 10px;"><strong>Invoice Date: </strong> <%=new DateUtils().dateWithFormat(invoices.getCreateddate(), "dd/MM/yyy")%></p>
            <p class="p3" style="  font-size: 10px;display: inline-block;vertical-align: top;"><strong>Invoice Amount: </strong><%=invoices.getAmount() %></p>
            <p class="p3" style="  font-size: 10px;display: inline-block;vertical-align: top;"><strong>Invoice Comments: </strong><%=invoices.getComments()%></p>

        </div>
        <hr/>
        <%} else {%>
        <table cellpadding="15" width="100%">
            <tbody>
                <tr>
                    <td width="20%" style="text-align: center;">No data found.</td>
                </tr>
            </tbody>
        </table>
        <%}%>
    </body>
</html>