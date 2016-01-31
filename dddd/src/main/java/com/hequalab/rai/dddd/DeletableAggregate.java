package com.hequalab.rai.dddd;

public abstract class DeletableAggregate<T extends Aggregate<ID>, ID extends Identity, D extends AggregateDeleted<ID>>
		extends DefaultAggregate<ID> {

	private final Class<ID> idType;

	private final Class<D> deletedType;

	private boolean deleted = false;

	@SuppressWarnings("unchecked")
	public DeletableAggregate(ID id, Class<D> deletedType) {
		super(id);
		this.deletedType = deletedType;
		this.idType = (Class<ID>) id.getClass();
	}

	@SuppressWarnings("unchecked")
	public T delete() {
		D event;
		try {
			event = deletedType.getConstructor(idType).newInstance(getId());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		applyChange(event);
		return (T) this;
	}

	public boolean isDeleted() {
		return deleted;
	}

	protected void setDeleted() {
		this.deleted = true;
	}

	@Override
	protected void applyChange(Event<?> event) {
		if (isDeleted()) {
			throw new IllegalStateException("Can't update a deleted aggregate");
		}
		super.applyChange(event);
	}

	@EventListener
	public void apply(AggregateDeleted<ID> event) {
		setDeleted();
	}

}