import { MinimalUser } from './user';

export abstract class Advertisement {
    id: Number;
    title: string;
    publicationDate: Date;
    expirationDate: Date;
    value: Number;
    user: MinimalUser;

}

export class FurnitureAdvertisement extends Advertisement {
    constructor() {
        super();
    }

}

export class JobAdvertisement extends Advertisement {
    candidate: Object; // change to Candidate - to be discussed

    constructor() {
        super();
    }
}

export class RealtyAdvertisement extends Advertisement {
    constructor () {
        super();
    }
}

export class ServiceAdvertisement extends Advertisement {
    scheduledDate: Date;

    constructor() {
        super();
    }
}