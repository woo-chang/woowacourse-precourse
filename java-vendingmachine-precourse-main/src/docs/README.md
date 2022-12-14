## 🚀요구 사항 분석

- 자판기가 보유하고 있는 금액을 무작위로 생성한다.
    - Randoms의 pickNumberList를 활용한다.
- 보유한 최소 개수의 동전으로 잔돈을 돌려준다.
- 상품명, 가격, 수량을 입력하여 상품을 추가할 수 있다.
    - 상품 가격은 100원부터 시작하며 10원으로 나누어떨어져야 한다.
    - 100원보다 작은 경우 예외가 발생한다.
    - 10원으로 나누어떨어지지 않는 경우 예외가 발생한다.
- 투입한 금액으로 상품을 구매하는데 최저 가격보다 적거나 모든 상품이 소진되었으면 바로 잔돈을 돌려준다.
    - 최저 가격이 무엇인지 알고 있어야 한다.
- 잔돈을 반환할 수 없는 경우 잔돈으로 반환할 수 있는 금액만 반환한다.
    - 최소한의 개수로 반환해야 한다.

## ⚙️구현 기능 목록

- [x] 입력
    - [x] 자판기가 보유하고 있는 금액을 입력한다.
    - [x] 상품명, 가격, 수량을 입력한다.
    - [x] 투입 금액을 입력한다.
    - [x] 구매할 상품명을 입력한다.
- [x] 변환
    - [x] 입력받은 문자를 숫자로 변환한다.
- [x] 출력
    - [x] 자판기가 보유한 동전을 출력한다.
    - [x] 반환 가능한 잔돈을 출력한다.
    - [x] 남아있는 금액을 출력한다.
    - [x] 메시지를 출력한다.
    - [x] 에러 메시지를 출력한다.
- [x] 코인
    - [x] 자신의 금액을 반환한다.
- [x] 코인 저장소 생성기
    - [x] 코인 저장소를 생성한다.
- [x] 코인 저장소
    - [x] 보유하고 있는 코인 개수를 저장한다.
    - [x] 남아있는 코인을 반환한다.
- [x] 상품 정보
    - [x] 가격과 수량 정보를 저장한다.
    - [x] 상품 구매 후 상품 정보를 업데이트한다.
- [x] 상품 저장소 생성기
    - [x] 상품 저장소를 생성한다.
- [x] 상품 저장소
    - [x] 보유하고 있는 상품 정보를 저장한다.
    - [x] 해당 돈으로 상품을 살 수 있는지 확인한다.
    - [x] 상품 이름으로 상품을 구매한다.
- [x] 자판기
    - [x] 코인 박스와 상품 박스를 관리한다.
    - [x] 자판기가 사용 가능한 상태인지 확인한다.
    - [x] 자판기를 사용한다.
    - [x] 자판기에서 잔돈을 반환한다.
- [x] 자판기 컨트롤러
    - [x] 자판기를 동작시킨다.