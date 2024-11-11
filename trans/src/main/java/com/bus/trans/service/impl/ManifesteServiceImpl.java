package com.bus.trans.service.impl;

import com.bus.trans.model.ReservationInterurbain;
import com.bus.trans.model.TrajetInterurbain;
import com.bus.trans.repository.ReservationInterurbainRepository;
import com.bus.trans.repository.TrajetInterurbainRepository;
import com.bus.trans.service.ManifesteService;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

@Service
public class ManifesteServiceImpl implements ManifesteService {

    @Autowired
    private TrajetInterurbainRepository trajetRepository;

    @Autowired
    private ReservationInterurbainRepository reservationRepository;

    @Override
    public ByteArrayInputStream genererManifeste(Long trajetId) {
        TrajetInterurbain trajet = trajetRepository.findById(trajetId).orElse(null);
        List<ReservationInterurbain> reservations = reservationRepository.findByTrajetId(trajetId);

        // Utilisation du Document d'iText pour générer un PDF
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document, out);
            document.open();

            // Titre du manifeste
            Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14);
            Paragraph titre = new Paragraph("Manifeste de Voyage", font);
            titre.setAlignment(Element.ALIGN_CENTER);
            document.add(titre);
            document.add(Chunk.NEWLINE);

            // Détails du trajet
            font = FontFactory.getFont(FontFactory.HELVETICA, 12);
            document.add(new Paragraph("Lieu de départ : " + trajet.getLieuDepart(), font));
            document.add(new Paragraph("Lieu d'arrivée : " + trajet.getLieuArrivee(), font));
            document.add(new Paragraph("Date de départ : " + trajet.getHeureDepart(), font));
            document.add(new Paragraph("Date d'arrivée : " + trajet.getHeureArrivee(), font));
            document.add(new Paragraph("Chauffeur : " + (trajet.getVehicule().getChauffeur()), font));
            document.add(Chunk.NEWLINE);

            // Tableau des passagers
            PdfPTable table = new PdfPTable(3);
            table.setWidthPercentage(100);
            table.setWidths(new int[]{1, 3, 3});

            PdfPCell hcell;
            hcell = new PdfPCell(new Phrase("Numéro"));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Nom"));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Siège"));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            // Remplir le tableau avec les passagers
            int numeroPassager = 1;
            for (ReservationInterurbain reservation : reservations) {
                PdfPCell cell;

                cell = new PdfPCell(new Phrase(String.valueOf(numeroPassager++)));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(reservation.getNomPassager() + " " + reservation.getPrenomPassager()));
                cell.setPaddingLeft(5);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(reservation.getNumeroPlace()));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setPaddingRight(5);
                table.addCell(cell);
            }

            document.add(table);
            document.close();

        } catch (DocumentException ex) {
            ex.printStackTrace();
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
}
