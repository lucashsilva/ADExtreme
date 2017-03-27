import { MinimalUser } from './user';

export class Advertisement {
    id: Number;
    title: string;
    publicationDate: Date;
    expirationDate: Date;
    value: Number;
    user: MinimalUser;
    type: string;

    constructor(type?: string) {
        this.user = new MinimalUser();
        this.type = type;
    }

}

export class FurnitureAdvertisement extends Advertisement {
    constructor() {
        super("FURNITURE");
    }

}

export class JobAdvertisement extends Advertisement {
    candidate: Object; // change to Candidate - to be discussed

    constructor() {
        super("JOB");
    }
}

export class RealtyAdvertisement extends Advertisement {
    constructor () {
        super("REALTY");
    }
}

export class ServiceAdvertisement extends Advertisement {
    scheduledDate: Date;

    constructor() {
        super("SERVICE");
    }
}
