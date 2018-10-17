package com.springboot.chapter10.view;

import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfWriter;
import com.springboot.chapter10.service.PdfExportService;
import org.springframework.web.servlet.view.document.AbstractPdfView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class PdfView extends AbstractPdfView {
    //导入服务接口
    private PdfExportService pdfExportService = null;
    //创建对象时载入导出服务接口
    public PdfView(PdfExportService pdfExportService){
        this.pdfExportService=pdfExportService;
    }
    @Override
    protected void buildPdfDocument(Map<String, Object> map, Document document, PdfWriter pdfWriter, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        pdfExportService.make(map,document,pdfWriter,httpServletRequest,httpServletResponse);
    }
}
