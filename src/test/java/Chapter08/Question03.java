package Chapter08;

import org.junit.Test;

/**
 * Created by hajaekwon on 2019-04-25.
 */
public class Question03 {

    /**
     * (2000년 넘게 오래된) 유클리드 알고리즘은 최대공약수를 b가 0일 때는 gcd(a,b) = a,
     * 그 외는 gcd(b, rem(a,b))로 계산한다(여기서 rem은 나머지다) 분명히 gcd는 a또는 b가 음수인 경우에도
     * 음수가 될 수 없다(반대 부호 값이 더 큰 약수가 되기 때문이다) 이 알고리즘을 수학적(음수가 아닌)
     * 나머지를 생산하는 %, floorMod 그리고 rem 함수를 이용해 구현하라
     * 셋 중 어느 것을 이용할 때 음수 값과 관련한 불편이 가장 적은가?
     */
    @Test
    public void solution() {
        int a = 1029;
        int b = 1071;

        System.out.println(GCM_FloorMod(a,b));
        System.out.println(GCM_Moduler(a,b));
        System.out.println(GCM_Rem(a,b));
    }

    public int GCM_Moduler(int a, int b) {
        if (b == 0) {
            return a;
        }
        if (a < b) {
            a = a + b;
            b = a - b;
            a = a - b;
        }
        int r = a % b;
        return GCM_Moduler(b, r);
    }

    public int GCM_FloorMod(int a, int b) {
        if (b == 0) {
            return a;
        }
        if (a < 0 && b < 0) { // 둘다 음수일때는 둘다 부호역전을 해준다
            a = -a;
            b = -b;
        }
        int r = Math.floorMod(a, b);
        return GCM_FloorMod(b, r);
    }

    public int GCM_Rem(int a, int b) {
        if (b == 0) {
            return a;
        }
        if (a < b) {
            a = a + b;
            b = a - b;
            a = a - b;
        }
        int r = (int)Math.IEEEremainder((double)a, (double)b);
        return GCM_Rem(b, r);
    }

    /**
     * floorMod 는 나누는 수가 음수면 음수값을 결과로 준다.
     * 나눠지는 수가 음수면 양수값을 결과로 준다
     *
     * GCM는 음수가 될 수 없다
     * floorMod 사용시에는 둘다 음수일때만 조심해주면 음수 계산시에도 특별한 문제가 없다
     *
     * %, rem 사용시에는 한쪽이 음수면 계산 결과가 작아지지 않기 때문에 stackOverFlow가 발생한다
     * 추가적인 음수처리가 필요하다!
     */


}
