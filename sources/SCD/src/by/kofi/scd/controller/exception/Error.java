package by.kofi.scd.controller.exception;

import org.springframework.stereotype.Controller;

import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Map;

/**
 * Stacktrace
 *
 * @author harchevnikov_m
 *         Date: 13/11/11
 *         Time: 15:54
 */
@Controller
public class Error {
    private String stackTrace;

    public void setStackTrace(String stackTrace) {
        this.stackTrace = stackTrace;
    }

    public String getStackTrace() {
        // Get the current JSF context
        FacesContext context = FacesContext.getCurrentInstance();
        Map requestMap = context.getExternalContext().getRequestMap();

        // Fetch the exception
        Throwable ex = (Throwable) requestMap.get("javax.servlet.error.exception");

        // Create a writer for keeping the stacktrace of the exception
        StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		ex.printStackTrace(pw);
		String stackTrace = sw.toString();
		return stackTrace.replace(System.getProperty("line.separator"), "<br/>&nbsp;&nbsp;&nbsp;&nbsp;\n");
    }

    /**
     * Write the stack trace from an exception into a writer.
     *
     * @param ex Exception for which to get the stack trace
     * @param pw PrintWriter to write the stack trace
     */
    private void fillStackTrace(Throwable ex, PrintWriter pw) {
        if (null == ex) {
            return;
        }

        ex.printStackTrace(pw);

        // The first time fillStackTrace is called it will always
        //  be a ServletException
        if (ex instanceof ServletException) {
            Throwable cause = ((ServletException) ex).getRootCause();
            if (null != cause) {
                pw.println("Root Cause:");
                fillStackTrace(cause, pw);
            }
        } else {
            // Embedded cause inside the ServletException
            Throwable cause = ex.getCause();

            if (null != cause) {
                pw.println("Cause:");
                fillStackTrace(cause, pw);
            }
        }
    }
}
