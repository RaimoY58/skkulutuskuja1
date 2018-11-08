<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>

 <body>

    <h1> Elecricity Consumption in  Kuja1 </h1> <br/>


    <c:out value="${testVar}"/> <br/> </br>

   <table>


    <form:form action="printElectricityConsumption" method="POST">

        <td><input type="submit" value="Print all items" /></td></br>
         <tr>
               <th>Day Stamp  </th>
               <th>Total Meter read-out  </th>
               <th>Day Consumption</th>
          </tr>
          <tr></tr>
          <tr></tr>


        <c:forEach var="kulutus" items="${kulutukset}">

            <tr>
                <td>${kulutus.paiva}</td>
                <td>${kulutus.mlukema}</td>
                <td>${kulutus.plukema}</td>
            </tr>
        </c:forEach>


    </form:form>

    <form:form method="POST" action="saveConsumptions">

        <input type="submit" value="save Electricity Consumption values, but only once !!!" /></br></br>

    </form:form>

    <form:form method="POST" action="printOneDayConsumption">


       <input name="printOneDay"  />

       <input type="submit" value=" Input and Print one day consumption"/></br></br>

      <tr>
        <c:out value="${dayConsumption.paiva}"/>
        <c:out value="${dayConsumption.mlukema}"/>
        <c:out value="${dayConsumption.plukema}"/> </br></br>
      </tr>

     </form:form>

    </table>

  </body>
</html>
