package lol.pyr.polyprotocol.api;

import lol.pyr.polyprotocol.ProtocolVersion;

public interface TranslatorFactory {
    Translator getTranslator(ProtocolVersion from, ProtocolVersion to);
}
