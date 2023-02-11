package lol.pyr.polyprotocol.api;

import lol.pyr.polyprotocol.protocols.ProtocolVersion;

public interface TranslatorFactory {
    Translator getTranslator(ProtocolVersion from, ProtocolVersion to);
}
