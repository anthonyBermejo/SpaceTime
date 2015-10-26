<%@ page trimDirectiveWhitespaces="true" %>
<%@ page language="java" contentType="text/xml; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<?xml version="1.0" encoding="UTF-8"?>
<game>
<status>success</status>
<pilots>
<c:forEach var="pilot" items="${pilots}">
<pilot pilotName="${pilot.pilotName}" version="${pilot.version}" id="${pilot.id}"></pilot>
</c:forEach>
</pilots>
</game>