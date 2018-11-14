package com.buutcamp.dao;

import com.buutcamp.other.KulutusLukema;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

@Repository
public class KulutusDao {


    @Autowired
    private SessionFactory sessionFactory;

    @Transactional

    public List<KulutusLukema> getKulutusLukemat() {

        Session session = sessionFactory.getCurrentSession();


        Query<KulutusLukema> query = session.createQuery("from KulutusLukema", KulutusLukema.class);

        return query.getResultList();


    }

    @Transactional
    public KulutusLukema getYksiKulutuslukema(String date) {

        Session session = sessionFactory.getCurrentSession();
        Query<KulutusLukema> query = session.createQuery("from KulutusLukema where paiva=:munpaiva",KulutusLukema.class);
        query.setParameter("munpaiva",date);
        return query.getSingleResult();
    }

    @Transactional
    public void saveKulutusLukemat() {

        Session session=sessionFactory.getCurrentSession();
        String FILE_PATH ="C:/Users/rylit/oss/kulutustiedot2vuottamod.xls";
        String aika = "";
        double mlukema = 0;
        double paiva_kl = 0;
        //reaad electricity consumption read-outs from excel-file and save rows (lukema-aika, mittarilukema ja päiväkulutus
        // one by one into sk_db.kulutus_kuja1 table
        try {

            FileInputStream excelFile = new FileInputStream(new File(FILE_PATH));
            Workbook workbook = new HSSFWorkbook(excelFile);
            Sheet datatypeSheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = datatypeSheet.iterator();
            int cellNbr = 0;
            while (iterator.hasNext()) {
                Row currentRow = iterator.next();
                Iterator<Cell> cellIterator = currentRow.iterator();

                while (cellIterator.hasNext()) {

                    Cell currentCell = cellIterator.next();
                    //getCellTypeEnum shown as deprecated for version 3.15
                    //getCellTypeEnum ill be renamed to getCellType starting from version 4.0
                    if (currentCell.getCellTypeEnum() == CellType.STRING) {
                        aika = currentCell.getStringCellValue();
                        cellNbr++;

                    } else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
                        if (cellNbr == 1) {
                            mlukema = currentCell.getNumericCellValue();
                            cellNbr++;
                        } else {
                            paiva_kl = currentCell.getNumericCellValue();

                            cellNbr = 0;
                        }
                    }

                }
                KulutusLukema kulutus = new KulutusLukema(aika, mlukema, paiva_kl);
                session.save(kulutus);
            }
        } catch (
                FileNotFoundException e) {
            e.printStackTrace();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }



}
