package de.ohnet.feedtube.service;

import de.ohnet.feedtube.domain.Channel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface FeedTubeService {

    @ApiOperation(value = "Finds feed in youtube - channel by channel-id.", nickname = "receiveChannelData", response = String.class, responseContainer = "List", tags = {
            "info", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful operation", response = String.class ),
            @ApiResponse(code = 400, message = "Invalid tag value") })
    @RequestMapping(value = "/v1/receiveChannelData", produces = {
            "application/String" }, method = RequestMethod.GET)
    public ResponseEntity<String> receiveChannelData();

    @ApiOperation(value = "Finds feed in youtube - channel by channel-id.", nickname = "receiveChannelData", response = String.class, responseContainer = "List", tags = {
            "info", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful operation", response = String.class ),
            @ApiResponse(code = 400, message = "Invalid tag value") })
    @RequestMapping(value = "/v1/receiveChannelDataAsJson", produces = {
            "application/json" }, method = RequestMethod.GET)
    public ResponseEntity<String> receiveChannelDataAsJson();


    @ApiOperation(value = "Adds a channel to the personal subscription list.", nickname = "receiveChannelData", response = String.class, responseContainer = "List", tags = {
            "info", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful operation", response = String.class ),
            @ApiResponse(code = 400, message = "Invalid tag value") })
    @RequestMapping(value = "/v1/addChannel", produces = {
            "application/json" }, method = RequestMethod.PUT)
    public ResponseEntity<String> addChannel(@RequestParam String channelId, @RequestParam String channelName);


    @ApiOperation(value = "Deletes a chnnel form the subsciption list.", nickname = "receiveChannelData", response = String.class, responseContainer = "List", tags = {
            "info", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful operation", response = String.class ),
            @ApiResponse(code = 400, message = "Invalid tag value") })
    @RequestMapping(value = "/v1/deleteChannel", produces = {
            "application/json" }, method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteChannel(@RequestParam String channelId);

    @ApiOperation(value = "Gets a List of channels.", nickname = "receiveChannelData", response = String.class, responseContainer = "List", tags = {
            "info", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful operation", response = String.class ),
            @ApiResponse(code = 400, message = "Invalid tag value") })
    @RequestMapping(value = "/v1/getChannelList", produces = {
            "application/json" }, method = RequestMethod.GET)
    public ResponseEntity<List<Channel>> getChannelList();



}
