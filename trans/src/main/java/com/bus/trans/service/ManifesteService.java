package com.bus.trans.service;

import java.io.ByteArrayInputStream;

public interface ManifesteService {
    ByteArrayInputStream genererManifeste(Long trajetId);
}
