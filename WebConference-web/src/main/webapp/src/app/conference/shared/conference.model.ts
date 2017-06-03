import {User} from "../../user/shared/user.model";
/**
 * Created by tudor on 06-May-17.
 */
export class Conference {
  name: string;
  date: string;
  deadline: string;
  ownerUsername: string;
  reviewers: User[];
  attenders: User[];
  speakers: User[];
}
