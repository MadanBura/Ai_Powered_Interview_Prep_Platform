import { useQuery } from '@tanstack/react-query';
import { profileService } from '../../services/profile.service';
import { PROFILE_QUERY_KEYS } from '../../profile.query-keys';

export const useGetProfile = () => {
  return useQuery({
    queryKey: PROFILE_QUERY_KEYS.all,
    queryFn: () => profileService.getProfile()
  });
};
