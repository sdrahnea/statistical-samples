package com.udc.controller;

import com.udc.model.CorruptionIndex;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by sdrahnea
 */
@RestController
@RequestMapping(value = "/cpi")
public class CorruptionIndexController extends AbstractController<CorruptionIndex> {

}