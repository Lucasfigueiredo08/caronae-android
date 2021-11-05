package com.unifap.caronaeunifap.ACRAreport;

import android.content.Context;
import androidx.annotation.NonNull;

import org.acra.config.ACRAConfiguration;
import org.acra.sender.ReportSender;
import org.acra.sender.ReportSenderFactory;

public class CrashReportFactory implements ReportSenderFactory {
    @NonNull
    @Override
    public ReportSender create(@NonNull Context context, @NonNull ACRAConfiguration config) {
        return new CrashReportSender();
    }
}
