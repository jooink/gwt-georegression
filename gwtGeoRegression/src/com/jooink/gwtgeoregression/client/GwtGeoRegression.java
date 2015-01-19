package com.jooink.gwtgeoregression.client;

import java.util.logging.Level;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.jooink.gwtejml.client.logPanel.LogPanel;
import com.jooink.gwtgeoregression.client.test.ExampleMetricLine;
import com.jooink.gwtgeoregression.client.test.ExampleRotationParameterizations;
import com.jooink.gwtgeoregression.client.test.ExampleTransformFitting;
import com.jooink.gwtgeoregression.client.test.ExampleTransformSequence;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class GwtGeoRegression implements EntryPoint {

	@Override
	public void onModuleLoad() {
		final ListBox lb=new ListBox();
		lb.addItem("Select an operation");
		lb.addItem("Example Metric Line");
		lb.addItem("Example Rotation Parametrization");
		lb.addItem("Example Transform Fitting");
		lb.addItem("Example Transform Sequence");
		RootPanel.get().add(lb);

		HTML ht=new HTML("---");
		RootPanel.get().add(ht);
		final LogPanel lp=new LogPanel(Level.ALL,false,false);
		lp.setTitle("Results");
		RootPanel.get().add(lp.getWidget());
		// --		
		lb.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				lp.clear();
				int index=lb.getSelectedIndex();
				
				switch (index) {
				case 1:
					Scheduler.get().scheduleDeferred(new ScheduledCommand() {    
						@Override
						public void execute() {
							ExampleMetricLine.init();
						}
					});
					break;
				case 2:
					Scheduler.get().scheduleDeferred(new ScheduledCommand() {    
						@Override
						public void execute() {
							ExampleRotationParameterizations.init();
						}
					});
					break;
				case 3:
					Scheduler.get().scheduleDeferred(new ScheduledCommand() {    
						@Override
						public void execute() {
							ExampleTransformFitting.init();
						}
					});
					break;
				case 4:
					Scheduler.get().scheduleDeferred(new ScheduledCommand() {    
						@Override
						public void execute() {
							ExampleTransformSequence.init();
						}
					});
					break;
				}
			}
		});
	}
	
	
}
