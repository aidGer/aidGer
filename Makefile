all:
	xgettext -D src/de/aidger --files-from=lang/POTFILES -d aidger -p lang -L Java --from-code=utf-8 -j --force-po --output=aidger.pot -k_
	msgmerge --update lang/de.po lang/aidger.pot; done

install:
	msgcat --properties-output lang/de.po -o lang/de.properties
	cp lang/*.properties ~/.config/aidGer/lang/
	



