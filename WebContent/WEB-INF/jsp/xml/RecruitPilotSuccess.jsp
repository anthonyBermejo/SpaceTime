<%@ page trimDirectiveWhitespaces="true" %>
<%@ page language="java" contentType="text/xml; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<?xml version="1.0" encoding="UTF-8"?>
<game>
<status>success</status>
<pilot version="${pilot.version}" id="${pilot.id}" pilotname="${pilot.pilotName}">
<player firstName="${player.firstName}" lastName="${player.lastName}" email="${player.email}" version="${player.version}" id="${player.id}"></player>
</pilot>
</game>