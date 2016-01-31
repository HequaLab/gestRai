package com.hequalab.rai.api.params;

/*
 * Class generated by AppWizard
 */

import io.dropwizard.jersey.params.AbstractParam;
import com.hequalab.rai.domain.richiestanuovoservizio.RichiestaNuovoServizioId;

public class RichiestaNuovoServizioIdParam extends AbstractParam<RichiestaNuovoServizioId> {

	public RichiestaNuovoServizioIdParam(String input) {
		super(input);
	}

	@Override
	public RichiestaNuovoServizioId parse(String input) throws Exception {
		return new RichiestaNuovoServizioId(input);
	}

}