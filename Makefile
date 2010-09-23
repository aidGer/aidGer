all:
	ant release

dist:
	ant dist

lang-gen:
	rm lang/aidger.pot
	touch lang/aidger.pot
	xgettext -D src/de/aidger --files-from=lang/POTFILES -d aidger -p lang -L Java --from-code=utf-8 -j --force-po --output=aidger.pot -k_
	msgmerge --update lang/de.po lang/aidger.pot

lang-install: lang-gen
	msgcat --properties-output lang/de.po -o lang/de.properties
	cp lang/*.properties src/de/aidger/res/lang/
	cp lang/*.properties ~/.config/aidGer/lang/
	cp docs/Handbuch.pdf src/de/aidger/res/pdf/



