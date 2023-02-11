package org.erick.batch.readers.file.reader;

import org.erick.batch.domain.CashierOperator;
import org.erick.batch.domain.OperationsCashier;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.ItemStreamReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.ResourceAwareItemReaderItemStream;
import org.springframework.core.io.Resource;

public class FileMultiCashierReader implements ItemStreamReader<CashierOperator>, ResourceAwareItemReaderItemStream<CashierOperator> {
	private Object actualObject;
	private FlatFileItemReader<Object> delegate;
	
	public FileMultiCashierReader(FlatFileItemReader<Object> delegate) {
		this.delegate = delegate;
	}

	@Override
	public void open(ExecutionContext executionContext) throws ItemStreamException {
		delegate.open(executionContext);
	}

	@Override
	public void update(ExecutionContext executionContext) throws ItemStreamException {
		delegate.update(executionContext);
	}

	@Override
	public void close() throws ItemStreamException {
		delegate.close();
	}

	@Override
	public CashierOperator read()
			throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		if (actualObject == null) {
			actualObject = delegate.read();
		}
		
		CashierOperator cashierOperator = (CashierOperator) actualObject;
		actualObject = null;
		if (cashierOperator != null) {
			while(peek() instanceof OperationsCashier) {
				cashierOperator.getOperations().add((OperationsCashier) actualObject);
			}
		}
		return cashierOperator;
	}

	private Object peek() throws Exception {
		actualObject = delegate.read();
		return actualObject;
	}

	@Override
	public void setResource(Resource resource) {
		delegate.setResource(resource);
	}


}
