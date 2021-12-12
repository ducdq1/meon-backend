package com.mrlep.meon.controllers;

import com.google.gson.Gson;
import com.mrlep.meon.config.ConfigValue;
import com.mrlep.meon.dto.request.CreateMediaRequest;
import com.mrlep.meon.services.MediaService;
import com.mrlep.meon.utils.Constants;
import com.mrlep.meon.utils.FnCommon;
import com.mrlep.meon.utils.MediaValidateUtils;
import com.mrlep.meon.utils.TeleCareException;
import com.mrlep.meon.xlibrary.core.constants.FunctionCommon;
import io.grpc.internal.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/medias")
public class MediasController {

    @Autowired
    private MediaService mediaService;

    @Autowired
    private ConfigValue configValue;

    @RequestMapping(path = "/upload", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> uploadAccountAvartar(@RequestParam("files") MultipartFile[] files,
                                                       @RequestParam("body") String body, @AuthenticationPrincipal Authentication authentication) {

        Object result = null;
        try {
            MediaValidateUtils.validateFileType(files, configValue);
            CreateMediaRequest request = new Gson().fromJson(body, CreateMediaRequest.class);
            Integer userId = FnCommon.getUserIdFromToken(authentication);
            result = mediaService.uploadFile(files, userId, request);
        } catch (TeleCareException e) {
            return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(FunctionCommon.responseToClient(result), HttpStatus.OK);
    }

    @RequestMapping(path = "/shop/{shopId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getMediasByShop(@PathVariable Integer shopId,
                                                  @RequestParam("objectType") String objectType,
                                                  @RequestParam(value = "mediaType",defaultValue = "",required = false) String mediaType, @RequestParam(value = "startRecord", required = false, defaultValue = "0") Integer startRecord,
                                                  @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize, @AuthenticationPrincipal Authentication authentication) {

        Object result = null;
        try {
            Integer userId = FnCommon.getUserIdFromToken(authentication);
            result = mediaService.getMediasByShop(shopId, objectType,mediaType, startRecord, pageSize);
        } catch (TeleCareException e) {
            return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(FunctionCommon.responseToClient(result), HttpStatus.OK);
    }

    @DeleteMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> deleteMedia(@PathVariable Integer mediaId, @AuthenticationPrincipal Authentication authentication) {

        Object result = null;
        try {
            Integer userId = FnCommon.getUserIdFromToken(authentication);
            result = mediaService.deleteMedia(mediaId, userId);
        } catch (TeleCareException e) {
            return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(FunctionCommon.responseToClient(result), HttpStatus.OK);
    }

    @DeleteMapping(path = "/media-category/{mediaId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> deleteMediaCategory(@PathVariable Integer mediaId, @AuthenticationPrincipal Authentication authentication) {

        Object result = null;
        try {
            Integer userId = FnCommon.getUserIdFromToken(authentication);
            result = mediaService.deleteMediaCategory(mediaId, userId);
        } catch (TeleCareException e) {
            return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(FnCommon.responseToClient(e), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(FunctionCommon.responseToClient(result), HttpStatus.OK);
    }

}
