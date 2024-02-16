import os
import glob

# 현재 작업 디렉토리를 검색할 디렉토리로 설정
root_dir = os.getcwd()

# 결과를 저장할 파일 설정
output_file = 'output_text_files.txt'

# 지원하는 파일 확장자 목록
extensions = ['*.java', '*.xml', '*.properties']

# 결과 파일 열기
with open(output_file, 'w', encoding='utf-8') as output:
    # 각 확장자에 대해 반복
    for ext in extensions:
        # 지정된 디렉토리와 하위 디렉토리에서 파일 검색
        for filename in glob.glob(os.path.join(root_dir, '**', ext), recursive=True):
            # 파일 이름과 확장자 추출
            base_name = os.path.basename(filename)
            # 파일 내용 읽기
            with open(filename, 'r', encoding='utf-8') as file:
                content = file.read()
            # 결과 파일에 내용 쓰기
            output.write(f'**{base_name}**\n\n{content}\n\n\n')

