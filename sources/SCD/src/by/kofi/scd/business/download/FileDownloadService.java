package by.kofi.scd.business.download;

import by.kofi.scd.dto.UserContext;
import by.kofi.scd.exceptions.SCDBusinessException;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;

/**
 * Service to download file
 *
 * @author harchevnikov_m
 *         Date: 27/10/11
 *         Time: 22:27
 */
@Service
public class FileDownloadService {

    private static final Logger LOGGER = Logger.getLogger(FileDownloadService.class);

    /**
     * Download file(write in http request) and remove it from temp directory
     *
     * @param userContext user context
     * @throws by.kofi.scd.exceptions.SCDBusinessException
     *          error
     */
    public void downloadFile(File file, UserContext userContext) throws SCDBusinessException {
        //todo implement
        try {
            ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();

            HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();
            response.setDateHeader("Expires", 0);
            response.setContentType("application/ms-excel");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"");
            ServletOutputStream outputStream = response.getOutputStream();
            fastChannelCopy(Channels.newChannel(new FileInputStream(file)), Channels.newChannel(outputStream));

            outputStream.close();
            //todo remove file
        } catch (IOException e) {
            LOGGER.error("download report for: " + userContext);
            throw new SCDBusinessException("download report for: " + userContext, e);
        }
    }

    private void fastChannelCopy(final ReadableByteChannel src, final WritableByteChannel dest) throws IOException {
        final ByteBuffer buffer = ByteBuffer.allocateDirect(16 * 1024);
        while (src.read(buffer) != -1) {
            buffer.flip();
            dest.write(buffer);
            buffer.compact();
        }
        buffer.flip();
        while (buffer.hasRemaining()) {
            dest.write(buffer);
        }
    }
}
