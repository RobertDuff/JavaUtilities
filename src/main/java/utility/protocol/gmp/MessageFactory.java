package utility.protocol.gmp;

import utility.protocol.gmp.messages.Header;
import utility.protocol.gmp.messages.Message;

public interface MessageFactory
{
	Message createMessage ( Header header, byte[] payload );
}
