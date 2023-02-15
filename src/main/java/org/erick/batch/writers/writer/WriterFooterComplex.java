package org.erick.batch.writers.writer;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

import org.erick.batch.domain.CashierOperator;
import org.springframework.batch.core.annotation.BeforeWrite;
import org.springframework.batch.item.file.FlatFileFooterCallback;
import org.springframework.stereotype.Component;

@Component
public class WriterFooterComplex implements FlatFileFooterCallback {
	
	private Double total = 0.0;

	@Override
	public void writeFooter(Writer writer) throws IOException {
		writer.append("\n---------------------------------------");
		writer.append("\n\t TOTAL: " + total);
		writer.append("\n---------------------------------------");
		writer.append("\n\t FILE AUTHENTICATION: sfsaeqodisafj12dslkf1" );
		writer.append("\n---------------------------------------");
	}
	
	@BeforeWrite
	public void beforeWrite(List<CashierOperator> operators) {
		for (CashierOperator operator : operators) {
			total += operator.getTotalTransaction();
		}
	}

}
