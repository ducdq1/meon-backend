package com.viettel.etc.xlibrary.core.config.log.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface LoggingService {
  void logRequest(HttpServletRequest paramHttpServletRequest, Object paramObject);
  
  void logResponse(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse, Object paramObject);
}


/* Location:              D:\DU_AN\MeOn\meon-backend\libs\com\viettel\telecare\libs\telecarelibs\1.0-RELEASE\telecarelibs-1.0-RELEASE.jar!\com\viettel\etc\xlibrary\core\config\log\service\LoggingService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */